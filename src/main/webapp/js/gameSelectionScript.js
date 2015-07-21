 $(function(){
	 
	 /*
	 
	 $.ajax({
	 	url: "http:\\localhost:8080\battleships\game",
	 	method: "GET",
	 	success: function(data){gameEditing(data)},
	 	error
	 });*/
	 
	 editingScreen = gameEditing();	//ONLY FOR EXAMPLE PURPOSE...
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
 	
 	return {
 		gameBoard : gameBoard,
 		transformationBoard : transformationBoard,
 		selectionBoard : selectionBoard	
 	}
 	
}
 
	// Stuff to do:
 /*
  * 1. JQuery Ajax requests.
  * 3. Verify if two shapes collide
  * 4. DROP SHADOW
  * 5. Information Vector : position, rotation, reflection.
  */