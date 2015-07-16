var generateBoard = function(){
	var mGameBoard;
	var nGameBoard;
	var $container;
 	
	//draws transfomations container whit m x n cells
	var drawTransformationContainer = function(m, n){
		var $container = $('.transformation-container');
		var $grid = $container.find(".transformation-grid");
		
		for(var i = 0; i < n ; i++){
			var $divRow = $('<div><div>');
			$divRow.addClass('div-row');
			for(var j = 0; j < m; j++){
				var $div = $('<div></div>');
				$divRow.append($div);
			}
			
			$grid.append($divRow);
		}
		
		$container.data( "size", { lines: n, columns: m});
		
	} 


	return{
		drawTransformationContainer : drawTransformationContainer
	}
};

var Transforms = {		
		rotateCounterClockwise : function(position){
			return {
				x: -position.y,
				y: position.x
			}
		},		
		rotateClockwise : function(position){
			return {
				x: position.y,
				y: -position.x
			}
		},		
		reflectX : function(position){
			return {
				x : position.x,
				y : -position.y
			}
		},		
		reflectY : function(position){
			return {
				x: -position.x,
				y: position.y
			}	
		},
		translate : function(position,x,y){
			return {
				x : position.x+x,
				y : position.y+y
			}
		}
}


var BattleshipPosition = function(xCoords, yCoords, offsetCellX, offsetCellY){
	
	var transforms = [];
	var cartesianCoords = [];
	this.offsetCellX = offsetCellX;
	this.offsetCellY = offsetCellY;
	
	var initialCoords = [];
	for (var i=0;i<xCoords.length;i++){
		initialCoords.push({
			x:xCoords[i],
			y:yCoords[i]
		});
	}
	
	var reset = function(){
		transforms = [];
		cartesianCoords = [];
		for (var i=0;i<initialCoords.length;i++){
			cartesianCoords.push({
				x:initialCoords[i].x+0.5,
				y:initialCoords[i].y+0.5
			});
		}
	};
	
	//Compute the initial coords
	reset();
	
	var reflectX = function(){
		transforms.push(Transforms.reflectX);
		for (var i=0;i<cartesianCoords.length;i++){
			cartesianCoords[i] = Transforms.reflectX(cartesianCoords[i]);			
		}
	};
	
	var reflectY = function(){
		transforms.push(Transforms.reflectY);
		for (var i=0;i<cartesianCoords.length;i++){
			cartesianCoords[i] = Transforms.reflectY(cartesianCoords[i]);			
		}
	};
	
	var rotateClockwise = function(){
		transforms.push(Transforms.rotateClockwise);
		for (var i=0;i<cartesianCoords.length;i++){
			cartesianCoords[i] = Transforms.rotateClockwise(cartesianCoords[i]);			
		}
	};
	
	var rotateCounterClockwise = function(){
		transforms.push(Transforms.rotateCounterClockwise);
		for (var i=0;i<cartesianCoords.length;i++){
			cartesianCoords[i] = Transforms.rotateCounterClockwise(cartesianCoords[i]);			
		}
	};
	
	var getCartesianCoords = function(){
		var result = [];
		for (var i=0;i<cartesianCoords.length;i++){
			result.push(cartesianCoords[i]);
		}
		return result;
	};
	
	var getCoords = function(){
		var result = [];
		for (var i=0;i<cartesianCoords.length;i++){
			result.push(Transforms.translate(cartesianCoords[i],-0.5,-0.5));
		}
		return result;
	};
	
	var getXCoords = function(){
		var coords = getCoords();
		var result = [];
		for (var i=0;i<coords.length;i++){
			result.push(coords[i].x);
		}
		return result;
	};
	
	var getYCoords = function(){
		var coords = getCoords();
		var result = [];
		for (var i=0;i<coords.length;i++){
			result.push(coords[i].y);
		}
		return result;
	};
	
	
	return {
		reflectX : reflectX,
		reflectY : reflectY,
		rotateClockwise : rotateClockwise,
		rotateCounterClockwise : rotateCounterClockwise,
		getCartesianCoords : getCartesianCoords,
		getCoords : getCoords,
		getXCoords : getXCoords,
		getYCoords : getYCoords,
//		undo : undo,
//		redo : redo,
		reset : reset
	};
}


 $(function(){
	 
	 /*
	 
	 $.ajax({
	 	url: "http:\\localhost:8080\battleships\game",
	 	method: "GET",
	 	success: function(data){gameEditing(data)},
	 	error
	 });*/
	 
	 gameEditing();	//ONLY FOR EXAMPLE PURPOSE...
 })
 
var gameEditing = function(data){
	 
	var $selectedShip = null;
	var $draggingShip = null;
	
	var $container =  $($('.board-container')[0]);
 	var board = new Board(9, 9, $container);
 	board.drawBoard();
 		
 	var $transformationContainer = $('.transformation-container');
 	var transformationBoard = new TransformationBoard(10, 10, $transformationContainer, "transformShip", board);
 	transformationBoard.drawBoard();
 	
	var $shipsContainer = $('.ships-container');
	selectionBoard = new SelectionBoard(23, 4, $shipsContainer, "modelShip", transformationBoard);
	selectionBoard.drawBoard();
	
	$(document).on("selectedShipChanged",function(event,data){
		$selectedShip = data;
	});

 	/*	Draw ships in ships-container - June 25. 2015. added idIndex, so 
 	 * 	redraw function could be easier. Supposed BUGS could be available   */
	var offset = 0;
	
 	var x = [0,1,2,1];
	var y = [0,0,0,1];
 	selectionBoard.drawShip(x, y);

 	var x1 = [0,1,2,1,1];
 	var y1 = [1,0,1,1,2];
 	selectionBoard.drawShip(x1, y1);
 
 	var x2 = [0,0,0,1];
 	var y2 = [0,1,2,0];
 	selectionBoard.drawShip(x2, y2);

 	var x3 = [0,1,2];
 	var y3 = [0,0,0];
 	selectionBoard.drawShip(x3, y3);

 	var x4 = [0,1,2,3,4];
 	var y4 = [0,0,0,0,0];
 	selectionBoard.drawShip(x4, y4);


	
 	//Dragging
	
 	$('.transformation-container, .board-container').on('mouseover',function(){

 		$('.transformShip, .gameBoardShip').draggable({
 	 		cursor: 'move',
 	 		revert: 'invalid',
 	 		start: function(event, ui) {
 	 			$draggingShip = $(this); 	 	 
 	 		}
 		})
 	});
 	
 	//	NO RIGHTCLICK MENU :D YEEEY
 	
 	$('.board-container').off("contextmenu.rclick",".gameBoardShip").on("contextmenu.rclick",".gameBoardShip",function(e){ 
	 		e.preventDefault();
	 		e.stopPropagation();
	 		return false;
 	});
 	
 	//	RightClick function: Needs to be edited, info below.

	$('.board-container').off("mousedown.rclick",".gameBoardShip").on("mousedown.rclick",".gameBoardShip",function(e){ 
		    if( e.button == 2 ) { 
		    	var $this = $(this);
		    	setTimeout(function (){
		    		$this.remove();
		    	},200);
		 		e.preventDefault();
		 		e.stopPropagation();
		 		return false;
		    } 
	});  

 	
 	
 	/////////////////////////////////06.23.2015 - Handlers/////////////////////////////////////////////////////////

 	
 	// Needs to be edited: store commands to vector(s).
 	var applyTransformation = function(transformationName){
 		var $ship = $('div.transformShip');
 		var position = $ship.data("position");
 		position[transformationName]();
 		var xCoords = $ship.data("position").getXCoords();
 		var yCoords = $ship.data("position").getYCoords();
 		$ship.remove();
 	 	board.drawShip(xCoords, yCoords, 6, 6, $transformationContainer);	
 	}
 	
 	
 			//ROTATIONS
 	$('button#left').click(function(){ 		
 		applyTransformation("rotateCounterClockwise");
 	});
 	
 	$('button#right').click(function(){
 		applyTransformation("rotateClockwise");
 	});
 	
 	$('button#reflectY').click(function(){
 		applyTransformation("reflectY");
 	});
 	
 	$('button#reflectX').click(function(){
 		applyTransformation("reflectX");
 	})
 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
 	$('.board-container').droppable({
 		revert: false,
 		tolerance:'fit',
 		over: function(event, ui){
 			
 			//DROP SHADOW HERE
 			
 		},
		deactivate: function(event, ui){
			
			var d1 = $(".board-container");
			var d2 = $(".transformation-grid");
			
			var $ship = $(ui.draggable);
			var dropPosition = $ship.position();
			
			var topPosition;
			var leftPosition;
			
			if ($ship.hasClass("gameBoardShip")){				
				topPosition = dropPosition.top;
				leftPosition = dropPosition.left;					
			} else {				
				//The offset of the game board (top-left corner)
				var offsetGameBoard = d1.offset();
				//The offset of the transformation board (top-left corner)
				var offsetTransformBoard = d2.offset();
				var rel = { left: offsetGameBoard.left - offsetTransformBoard.left, 
						top: offsetGameBoard.top - offsetTransformBoard.top };
				
				topPosition = dropPosition.top - rel.top;
				leftPosition = dropPosition.left - rel.left;
			}
			
    		if( leftPosition + $ship.width() < d1.width() &&
    			topPosition + $ship.height() < d1.height() &&
    			leftPosition > 0 &&
    			topPosition > 0)
    		{
    			if ($ship.hasClass("transformShip")){
    				$ship.addClass("gameBoardShip");
    				$ship.detach();
    				$ship.removeClass("transformShip");
    				$ship.appendTo(d1);
    			}			
    			
    			if(topPosition % 40 != 0){
    				topPosition = Math.round(topPosition / 40) * 40;
    			}


    			if(leftPosition % 40 != 0){
    				leftPosition = Math.round(leftPosition / 40) * 40;
    			}
    			

    			$ship.css('top', topPosition + 'px');
    			$ship.css('left', leftPosition + 'px');
    	
    			if ($selectedShip != null && $selectedShip.hasClass("modelShip")){
    				$selectedShip.remove();
    			}	
			}
		}
 	});
}
 
	// Stuff to do:
 /*
  * 1. JQuery Ajax requests.
  * 2. RightClick : Put back the Ship to its initial place in the model board. 
  * 3. Verify if two shapes collide
  * 4. DROP SHADOW
  * 5. Information Vector : position, rotation, reflection.
  */