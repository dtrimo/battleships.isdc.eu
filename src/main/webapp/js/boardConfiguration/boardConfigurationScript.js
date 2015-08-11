requirejs.config({
	baseUrl : "js",
	shim : {
		'jqueryUI' : 'jquery',
		'selectionBoard' : 'jqueryUI',
		'transformationBoard' : 'jqueryUI',
		'gameBoard' : 'jqueryUI'
	},
	paths : {
		'jquery' : 'lib/jquery-1.11.3',
		'jqueryUI' : 'lib/jquery-ui',
		'gameBoard' : 'boardConfiguration/gameBoard',
		'gameBoardController' : 'boardConfiguration/gameBoardController',
		'selectionBoard' : 'boardConfiguration/selectionBoard',
		'selectionBoardController' : 'boardConfiguration/selectionBoardController',
		'transformationBoard' : 'boardConfiguration/transformationBoard',
		'transformationBoardController' : 'boardConfiguration/transformationBoardController',
		'transform' : 'boardConfiguration/transformComponent',
		
		'battleshipModel' : 'board/battleshipModel',
		'battleshipView' : 'board/battleshipView',
		'coords' : 'board/coords',
		'transforms' : 'board/transforms',
		'board' : 'board/board'
	}
}); 

require(['jquery','battleshipModel','selectionBoard', 'selectionBoardController', 'gameBoard', 'gameBoardController', 'transformationBoard','transformationBoardController','transform'],function(
	$, BattleshipModel, SelectionBoard, SelectionBoardController, GameBoard, GameBoardController, TransformationBoard, TransformationBoardController, Transform){
	
	var createSelectionBoard = function($container, availableBattleships){	
		var selectionBoard = new SelectionBoard(23,4,$container,"modelShip",$.map(availableBattleships,function(battleship, index){
			return new BattleshipModel(
					battleship.av_BT_id,
					battleship.model.positions);
		}));
		selectionBoard.drawBoard();
		return selectionBoard;
	}
	
	var gameEditing = function(data){
		 
		var gameTypeData = $.parseJSON($("#gameConfigData").text());
		var selectionBoard = createSelectionBoard($('.ships-container'), gameTypeData.availableBTs);
		new SelectionBoardController(selectionBoard).init();
		
		var $transformationContainer = $('.transformation-container');
		var transformationBoard = new TransformationBoard(10, 10, $transformationContainer, "transformShip");
		transformationBoard.drawBoard();
		new TransformationBoardController(transformationBoard,transformationBoard.board.$container).init();
		
		var $gameContainer =  $('.board-container');
		gameBoard = new GameBoard(gameTypeData.m, gameTypeData.n, $gameContainer, "gameBoardShip");
		gameBoard.drawBoard();
		new GameBoardController(gameBoard).init();
		
	 	return {
	 		gameBoard : gameBoard,
	 		transformationBoard : transformationBoard,
	 		selectionBoard : selectionBoard	
	 	} 	
	};
	
	editingScreen = gameEditing();
});
 
// Stuff to do:
 /*
  * 1. JQuery Ajax requests.
  * 3. Verify if two shapes collide
  * 4. DROP SHADOW
  * 5. Information Vector : position, rotation, reflection.
  */