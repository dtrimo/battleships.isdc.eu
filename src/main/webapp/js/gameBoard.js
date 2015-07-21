(function($) {
	GameBoard = function(m, n, $container, shipClass, transformBoard, selectionBoard) {
		this.m = m;
		this.n = n;
		this.$container = $container;
		this.shipClass = shipClass;
		this.transformBoard = transformBoard;
		this.selectionBoard = selectionBoard;
		this.board = new Board(m, n, $container, shipClass);
		this.ships = [];
		bindDroppableBoardHandler(this);
		bindDraggableShipHandler(this);
		bindRightClickHandler(this);
	}
	
	GameBoard.prototype.drawBoard = function() {
		this.board.drawBoard();
	}
	
	GameBoard.prototype.addShip = function(ship) {
		this.ships.push(ship);
	}
	
	GameBoard.prototype.getShip = function(shipSelector){
		for (var i = 0; i < this.ships.length; i++){
			if (this.ships[i].$container.is($(shipSelector))){
				return this.ships[i];
			}
		}
		return null;
	}

	GameBoard.prototype.removeShip = function(shipSelector){
		var ship = this.getShip(shipSelector);
		if (ship != null){
			ship.$container.remove();
			this.ships.splice(this.ships.indexOf(ship),1);
		}
		
		return ship;
	}

	GameBoard.prototype.getShips = function(){
		return this.ships;
	}
	
	var bindRightClickHandler = function(gameBoard) {
		gameBoard.$container.on("mousedown.rclick", "." + gameBoard.shipClass,
				function(e) {
					if (e.button == 2) {
						var ship = gameBoard.getShip($(this));
						var initialPosition = Transform.resetInitialPosition(ship.position.getTransforms(), ship.position);
						gameBoard.selectionBoard.drawShip(initialPosition.getXCoords(), 
								initialPosition.getYCoords());
						gameBoard.removeShip($(this));						
					}
				}); 
	}

	var bindDraggableShipHandler = function(gameBoard) {
		gameBoard.$container.on('mouseover', function() {
			$("." + gameBoard.shipClass).draggable({
				cursor : 'move',
				revert : 'invalid',
			})
		});
	}

	var bindDroppableBoardHandler = function(gameBoard) {
		gameBoard.$container
				.droppable({
					revert : false,
					tolerance : 'fit',
					over : function(event, ui) {

						// DROP SHADOW HERE
						$('.board-container').css('background-color', 'green');

					},
					out : function(event, ui) {
						$('.board-container').css('background-color', 'white');
						$('.board-container').css('opacity', '0.4');
					},
					deactivate : function(event, ui) {

						var gameContainer = gameBoard.$container;
						// TODO: this is not okay, because i'm selecting the
						// transformBoard
						// and not the transformation-grid, which i removed from
						// the gameboard.jsp
						// because I found it useless. Hardcoding the value
						// ".transformation-grid"
						// here is not adviced(bad practice) and could really
						// give headaches.
						// this still works, but it's error exposed, the
						// calculation should behave
						// different.
						// initial value : $(".transformation-grid");
						var transformContainer = gameBoard.transformBoard.$container

						var $ship = $(ui.draggable);
						var dropPosition = $ship.position();

						var topPosition;
						var leftPosition;

						if ($ship.hasClass(gameBoard.shipClass)) {
							topPosition = dropPosition.top;
							leftPosition = dropPosition.left;
						} else {
							// The offset of the game board (top-left corner)
							var offsetGameBoard = gameContainer.offset();
							// The offset of the transformation board (top-left
							// corner)
							var offsetTransformBoard = transformContainer
									.offset();
							var rel = {
								left : offsetGameBoard.left
										- offsetTransformBoard.left,
								top : offsetGameBoard.top
										- offsetTransformBoard.top
							};

							topPosition = dropPosition.top - rel.top;
							leftPosition = dropPosition.left - rel.left;
						}

						if (leftPosition + $ship.width() < gameContainer.width()
								&& topPosition + $ship.height() < gameContainer.height()
								&& leftPosition > 0
								&& topPosition > 0) {

							if ($ship.hasClass(gameBoard.transformBoard.shipClass)) {
								gameBoard.addShip(gameBoard.transformBoard.getShip());
								//TODO: do your stuff
								
								// if the ship is successfuly dropped inside
								// gameBoard,
								// the ship is removed from the transform board
								gameBoard.transformBoard.removeShip();

								$ship.addClass(gameBoard.shipClass);
								$ship.detach();
								$ship.removeClass(gameBoard.transformBoard.shipClass);
								$ship.appendTo(gameContainer);
							}

							if (topPosition % 40 != 0) {
								topPosition = Math.round(topPosition / 40) * 40;
							}

							if (leftPosition % 40 != 0) {
								leftPosition = Math.round(leftPosition / 40) * 40;
							}

							$ship.css('top', topPosition + 'px');
							$ship.css('left', leftPosition + 'px');
						}
					}
				});
	}
})(jQuery);