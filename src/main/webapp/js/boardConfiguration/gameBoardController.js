define(['jquery','gameBoard'],function($,GameBoard){
	
	var GameBoardController = function(gameBoard){
		this.gameBoard = gameBoard;
	}
	
	GameBoardController.prototype.init = function(){
		bindDroppableBoardHandler(this.gameBoard);
		bindDraggableShipHandler(this.gameBoard);
		bindRightClickHandler(this.gameBoard);
		bindMouseMove(this.gameBoard);
	}
	
	GameBoardController.prototype.destroy = function(){
		
	}
	
	var bindRightClickHandler = function(gameBoard) {
		gameBoard.board.$container.on("mousedown.gameboard", "." + gameBoard.board.shipClass,
			function(e) {
				if (e.button == 2) {
					var $divShip = $(this);
					var battleshipView = $divShip.data("shipView");
					gameBoard.removeShip(battleshipView);
					battleshipView.destroy();
					$(document).trigger("shipRemoved.gameBoard",battleshipView);				
				}
			}); 
	}

	var bindDraggableShipHandler = function(gameBoard) {
		gameBoard.board.$container.on('mouseover', function() {
			$("." + gameBoard.shipClass).draggable({
				cursor : 'move',
				revert : 'invalid',
			})
		});
	}
	
	var computeDropOffset = function(x,y,cellHeight, cellWidth,
			boardWidthInCells, boardHeightInCells,shipWidthInCells, shipHeightInCells){
		//pointX and pointY represent the offsets in number of cells of the 
		//closest lattice point (this is where the top-left corner of the ship div should land).
		var pointX = Math.floor((x+cellWidth/2)/cellWidth);
		var pointY = Math.floor((y+cellHeight/2)/cellHeight);
		
		if (pointX < 0 || pointX > boardWidthInCells - shipWidthInCells){
			return null;
		}
		
		if (pointY < 0 || pointY > boardHeightInCells - shipHeightInCells){
			return null;
		}
		
		return {
			x : pointX,
			y : boardHeightInCells - pointY - shipHeightInCells
		}
	}
	
	var updateShadow = function (gameBoard, $divShip){
		var $boardContainer = gameBoard.board.$container;

		var dropOffset = $divShip.offset();
		var boardOffset = $boardContainer.offset();

		var relativeDropOffset = {
				x: dropOffset.left - boardOffset.left,
				y: dropOffset.top - boardOffset.top
		}
		
		var cellSize = $divShip.data("cell-size");
		var shipView = $divShip.data("shipView");
		
		var dropOffset = computeDropOffset(relativeDropOffset.x,relativeDropOffset.y,cellSize.width, cellSize.height,
				gameBoard.board.width, gameBoard.board.height,shipView.shipCellWidth, shipView.shipCellHeight);
		
		if (dropOffset == null){
			return null;
		}
		
		var drawNewShadow = false;
		var $previousShadow = $boardContainer.find(".shadow-view");
		if ($previousShadow.length > 0){
			var previousShadowOffset = $previousShadow.data("dropOffset");
			//we only redraw the shadow if its position changes
			if (dropOffset.x != previousShadowOffset.x || dropOffset.y != previousShadowOffset.y){
				$previousShadow.data("shipView").destroy();
				drawNewShadow = true;
			}		
		} else {
			drawNewShadow = true;
		}
		
		if (drawNewShadow){
			var shadowView = gameBoard.board.drawShip(shipView.getModel(),dropOffset.x,dropOffset.y, false);
			shadowView.$divShip.addClass("shadow-view");
			shadowView.$divShip.data("dropOffset",dropOffset);	
			
			//Add an appropriate shadow depending on whether dropping on the shadow is
			//ok or not
			if (gameBoard.isShipAdditionValid(shadowView,dropOffset.x,dropOffset.y)){
				if (!shadowView.$divShip.hasClass("valid")){					
					shadowView.$divShip.addClass("valid");
				}
				if (shadowView.$divShip.hasClass("invalid")){					
					shadowView.$divShip.removeClass("invalid");
				}
			} else {
				if (!shadowView.$divShip.hasClass("invalid")){					
					shadowView.$divShip.addClass("invalid");
				}
				if (shadowView.$divShip.hasClass("valid")){					
					shadowView.$divShip.removeClass("valid");
				}
			}
		}	
		
		return dropOffset;
	}
	
	var bindMouseMove = function(gameBoard){
		var highlightCurrentShip = function(event){
			var mouseX = event.pageX;
			var mouseY = event.pageY;
			var boardX = gameBoard.board.$container.offset().left;
			var boardY = gameBoard.board.$container.offset().top;
			var leftOffset = Math.floor((mouseX - boardX)/gameBoard.board.getCellWidth()); 
			var topOffset = Math.floor((mouseY - boardY)/gameBoard.board.getCellHeight()); 
			var currentCellX = leftOffset;
			var currentCellY = gameBoard.board.height - topOffset-1;
			var hoveredShipView = gameBoard.getShipViewForCell(currentCellX,currentCellY);
			if (hoveredShipView!=null){
				gameBoard.board.$container.find("."+gameBoard.board.shipClass).not(hoveredShipView.$divShip).removeClass("hovered");
				hoveredShipView.$divShip.addClass("hovered");	
				hoveredShipView.$divShip;
			} else {
				gameBoard.board.$container.find("."+gameBoard.board.shipClass).removeClass("hovered");
			}
		}
		gameBoard.board.$container.off("mousemove.gameBoard").on("mousemove.gameBoard",highlightCurrentShip);
	}
	
	var addShipHandlers = function(gameBoard,battleshipView,dropOffsetX, dropOffsetY){
		battleshipView.$divShip.draggable({
			handle : '.ship-cell',
			cursor : 'move',
			revert : 'invalid',
			start : function(){
				battleshipView.$divShip.data("dropOffset",{
					x : dropOffsetX,
					y : dropOffsetY
				});
			}
		});
	};
	
	var removeShadow = function(gameBoard){
		var $shadowViewDiv = gameBoard.board.$container.find(".shadow-view");
		if ($shadowViewDiv.length > 0){
			$shadowViewDiv.data("shipView").destroy();
		}
	};
	
	var bindDroppableBoardHandler = function(gameBoard) {
		var shipRevertTimeout = null;
		var revertPosition = null;
		gameBoard.board.$container.droppable({
			tolerance : {
				x : gameBoard.board.getCellWidth() / 2,
				y : gameBoard.board.getCellHeight() / 2
			},
			over : function(event, ui) {
				var $divShip = $(ui.draggable);
				$divShip.on("drag",function(event,ui){
					updateShadow(gameBoard,$divShip);
					if (shipRevertTimeout!=null){
						clearTimeout(shipRevertTimeout);
						shipRevertTimeout = null;
					}
				});				
			},
			out : function(event, ui) {
				var $divShip = $(ui.draggable);
				var shipView = $divShip.data("shipView");
				//move the shadow to the last valid position
				var $previousShadow = gameBoard.board.$container.find(".shadow-view");
				if ($previousShadow.length > 0){
					$previousShadow.data("shipView").destroy();
					var previousDropOffset = $divShip.data("dropOffset");
					if (previousDropOffset){
						var shadowView = gameBoard.board.drawShip(shipView.getModel(),previousDropOffset.x,previousDropOffset.y, false);
						shadowView.$divShip.addClass("shadow-view");
						shadowView.$divShip.data("dropOffset",previousDropOffset);
						if (!shadowView.$divShip.hasClass("valid")){					
							shadowView.$divShip.addClass("valid");
						}
						if (shadowView.$divShip.hasClass("invalid")){					
							shadowView.$divShip.removeClass("invalid");
						}
					}
				}
			},
			//this fires also when dropping in an invalid position
			deactivate : function(event, ui) {
				var $divShip = $(ui.draggable);
				if ($.contains(document,$divShip[0])){
					shipRevertTimeout = setTimeout(function(){
						removeShadow(gameBoard);
					},510);					
				} else {
					//the ship was removed by the drop handler. Replace the shadow with
					//a regular ship. OLD comment. please update
				}
			},
			//this is called only when dropping on the board (and +- half a cell)
			//this method is called before the draggable's revert method is called
			//and before the deactivate method is called
			drop : function (event, ui){
				clearTimeout(shipRevertTimeout);
				shipRevertTimeout = null;

				var $divShip = $(ui.draggable);
				var shipView = $divShip.data("shipView");
				var $shadowView = gameBoard.board.$container.find(".shadow-view");
				if ($shadowView.length == 0){
					//it shouldn't be possible to drop a ship which doesn't already have a shadow
					return;
				}
				var shadowShipView = $shadowView.data("shipView");
				var dropOffset = shadowShipView.$divShip.data("dropOffset");
				if (gameBoard.isShipAdditionValid(shipView, dropOffset.x, dropOffset.y)){
					shipView.destroy();					
					shadowShipView.destroy();
					var newShipView = gameBoard.board.drawShip(shipView.getModel(), dropOffset.x, dropOffset.y, false);
					gameBoard.addShip(newShipView,dropOffset.x,dropOffset.y);
					addShipHandlers(gameBoard,newShipView, dropOffset.x, dropOffset.y);
					$(document).trigger("shipAdded.gameBoard",newShipView);		
				} else {
					$divShip.draggable("option","revert",true);
				}
			}
		});
	}
	
	return GameBoardController;
	
});