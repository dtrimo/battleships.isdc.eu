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
		'gameScreenController' : 'gameScreen/gameScreenController',
		'gameCommunications' : 'gameScreen/gameCommunications',
		'gameTimer' : 'gameScreen/gameTimer',
		'selectionBoard' : 'boardConfiguration/selectionBoard',
		'selectionBoardController' : 'boardConfiguration/selectionBoardController',
		'transformationBoard' : 'boardConfiguration/transformationBoard',
		'transformationBoardController' : 'boardConfiguration/transformationBoardController',
		
		'battleshipModel' : 'board/battleshipModel',
		'battleshipView' : 'board/battleshipView',
		'coords' : 'board/coords',
		'transforms' : 'board/transforms',
		'board' : 'board/board'
	}
}); 

require(['jquery','transforms','battleshipModel','battleshipView','gameBoard', 'gameBoardController','gameScreenController', 'gameCommunications', 'gameTimer'],function(
	$, Transforms, BattleshipModel, BattleshipView, GameBoard, GameBoardController, GameScreenController, GameCommunications, GameTimer){
	
	var	gameScreen;
	var userId;
	var gameId;
	var startConfigId;
	var gameTimer;
	var stompClient;
	
	var initGameScreen = function(data){
		var gameTypeData = $.parseJSON($("#gameConfigData").text());
		
		var $gameContainer =  $('#current-player-board.board-container');
		var currentPlayerGameBoard = new GameBoard(gameTypeData.m, gameTypeData.n, $gameContainer, "gameBoardShip");
		currentPlayerGameBoard.drawBoard();
		new GameBoardController(currentPlayerGameBoard).init({
			bindRightClickHandler : false
		});
		
		$gameContainer =  $('#opponent-board.board-container');
		var gameBoard = new GameBoard(gameTypeData.m, gameTypeData.n, $gameContainer, "gameBoardShip");
		gameBoard.drawBoard();
		var gameScreenController = new GameScreenController(gameBoard, currentPlayerGameBoard);
		gameScreenController.init(gameTimer, stompClient);
		
	 	return {
	 		currentPlayerGameBoard : currentPlayerGameBoard,
	 		gameBoard : gameBoard,
	 		gameScreenController : gameScreenController
	 	} 	
	};
	
	var initBoardWithPrevData = function(){
		var data = JSON.parse($("#startConfigData").text());
		var ships = data.selectedPositions;
		for (var i=0;i<ships.length;i++){
			var shipModel = new BattleshipModel(ships[i].availableBattleshipId, ships[i].availableBattleshipPositions);
			for (var j=0;j<ships[i].transformations.length;j++){
				shipModel.applyTransform(Transforms[ships[i].transformations[j]]);
			}
			var shipView = new BattleshipView(shipModel);
			var newShipView = gameScreen.currentPlayerGameBoard.board.drawShip(shipView.getModel(), ships[i].xOffset, ships[i].yOffset, false);
			gameScreen.currentPlayerGameBoard.addShip(newShipView, ships[i].xOffset, ships[i].yOffset);
		}
	};
	
	var setupServerEventListener = function() {
		stompClient = GameCommunications.openWSConnection(userId,	gameId, startConfigId);
		GameCommunications.subscribe(stompClient,	"/game/" + gameId + "/" + userId, function(message) {
			if (message.type == "END_ROUND") {
				gameTimer.incrementRound();
				gameScreen.gameScreenController.processEndRound(message);
			} else if (message.type = "FIRE_RESULT"){
				gameScreen.gameScreenController.processFireResult(message);
			}
		});
	};
	
	$(function(){
		userId = Number($("#userId").text());
		gameId = Number($("#gameId").text());
		startConfigId = Number($("#startConfigId").text());
		
		gameTimer = new GameTimer();
		setupServerEventListener();
		
		gameScreen = initGameScreen();
		initBoardWithPrevData();
		gameTimer.start();
		
	});
});