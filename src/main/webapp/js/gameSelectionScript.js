//var Transforms = {		
//		rotateCounterClockwise : function(position){
//			return {
//				x: -position.y,
//				y: position.x
//			}
//		},		
//		rotateClockwise : function(position){
//			return {
//				x: position.y,
//				y: -position.x
//			}
//		},		
//		reflectX : function(position){
//			return {
//				x : position.x,
//				y : -position.y
//			}
//		},		
//		reflectY : function(position){
//			return {
//				x: -position.x,
//				y: position.y
//			}	
//		},
//		translate : function(position,x,y){
//			return {
//				x : position.x+x,
//				y : position.y+y
//			}
//		}
//}


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
//		var result = [];
//		for (var i=0;i<cartesianCoords.length;i++){
//			result.push(Transforms.translate(cartesianCoords[i],-0.5,-0.5));
//		}
//		return result;
		return initialCoords;
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
	
	//you need this so you can use transformations
	Transform = new Transform();
	
	var $transformationContainer = $('.transformation-container');
	var transformationBoard = new TransformationBoard(10, 10, $transformationContainer, "transformShip");
	
	var $shipsContainer = $('.ships-container');
	selectionBoard = new SelectionBoard(23, 4, $shipsContainer, "modelShip", transformationBoard);
	
	var $gameContainer =  $('.board-container');
	var gameBoard = new GameBoard(9, 9, $gameContainer, "gameBoardShip", transformationBoard, selectionBoard);
	
	gameBoard.drawBoard();
	transformationBoard.drawBoard();
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

 	
 	
 	// Needs to be edited: store commands to vector(s).
// 	var applyTransformation = function(transformationName){
// 		var $ship = $('div.transformShip');
// 		var position = $ship.data("position");
// 		position[transformationName]();
// 		var xCoords = $ship.data("position").getXCoords();
// 		var yCoords = $ship.data("position").getYCoords();
// 		$ship.remove();
// 	 	board.drawShip(xCoords, yCoords, 6, 6, $transformationContainer);	
// 	}
 	
 	
 			//ROTATIONS
// 	$('button#left').click(function(){ 		
// 		applyTransformation("rotateCounterClockwise");
// 	});
 	
// 	$('button#right').click(function(){
// 		applyTransformation("rotateClockwise");
// 	});
 	
 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
 	//TODO:put droppable here in case you can't figure it out(note)
}
 
	// Stuff to do:
 /*
  * 1. JQuery Ajax requests.
  * 3. Verify if two shapes collide
  * 4. DROP SHADOW
  * 5. Information Vector : position, rotation, reflection.
  */