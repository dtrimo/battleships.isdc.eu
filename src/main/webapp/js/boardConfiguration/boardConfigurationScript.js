requirejs
		.config({
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
				'gameCommunications' : 'gameScreen/gameCommunications',
				'gameTimer' : 'gameScreen/gameTimer',

				'battleshipModel' : 'board/battleshipModel',
				'battleshipView' : 'board/battleshipView',
				'coords' : 'board/coords',
				'transforms' : 'board/transforms',
				'board' : 'board/board'
			}
		});

require(
		[ 'jquery', 'transforms', 'battleshipModel', 'battleshipView',
				'selectionBoard', 'selectionBoardController', 'gameBoard',
				'gameBoardController', 'transformationBoard',
				'transformationBoardController', 'gameCommunications', 'gameTimer' ],
		function($, Transforms, BattleshipModel, BattleshipView,
				SelectionBoard, SelectionBoardController, GameBoard,
				GameBoardController, TransformationBoard,
				TransformationBoardController, GameCommunications, GameTimer) {

			var editingScreen;
			var shipCount;
			var userId;
			var gameId;
			var startConfigId;
			var gameTimer;

			var createSelectionBoard = function($container,
					availableBattleships) {
				var selectionBoard = new SelectionBoard(26, 5, $container,
						"modelShip", $.map(availableBattleships, function(
								battleship, index) {
							return new BattleshipModel(
									battleship.availableBattleshipId,
									battleship.model.positions);
						}));
				selectionBoard.drawBoard();
				return selectionBoard;
			};

			var gameEditing = function(data) {
				var gameTypeData = $.parseJSON($("#gameConfigData").text());
				shipCount = gameTypeData.availableBTs.length;
				var selectionBoard = createSelectionBoard(
						$('.ships-container'), gameTypeData.availableBTs);
				new SelectionBoardController(selectionBoard).init();

				var $transformationContainer = $('.transformation-container');
				var transformationBoard = new TransformationBoard(10, 10,
						$transformationContainer, "transformShip");
				transformationBoard.drawBoard();
				new TransformationBoardController(transformationBoard,
						transformationBoard.board.$container).init();

				var $gameContainer = $('.board-container');
				gameBoard = new GameBoard(gameTypeData.m, gameTypeData.n,
						$gameContainer, "gameBoardShip");
				gameBoard.drawBoard();
				new GameBoardController(gameBoard).init();

				return {
					gameBoard : gameBoard,
					transformationBoard : transformationBoard,
					selectionBoard : selectionBoard
				}
			};

			var bindStartButtonStateChangeHandler = function() {
				$(document)
						.on(
								"ship-added",
								function(event, board) {
									if (board === editingScreen.gameBoard
											&& board.containedBattleshipViews.length == shipCount) {
										$("a.start-game").removeClass(
												"disabled");
									}
								});
				$(document).on("ship-removed", function(event, board) {
					if (board === editingScreen.gameBoard) {
						$("a.start-game").addClass("disabled");
					}
				});
			};

			var bindStartButtonClickHandler = function() {
				$("a.start-game")
						.on(
								"click",
								function(event) {
									if ($(this).hasClass("disabled")) {
										return;
									}
									var battleshipViews = gameBoard
											.getContainedBattleshipViews();
									var data = $
											.map(
													battleshipViews,
													function(obj) {
														return {
															availableBattleshipId : obj.shipView.battleshipModel.id,
															transformations : $
																	.map(
																			obj.shipView.battleshipModel.transforms,
																			function(
																					transform) {
																				return transform.name;
																			}),
															xOffset : obj.xOffset,
															yOffset : obj.yOffset,
															occupiedPositions : $
																	.map(
																			obj.shipView.battleshipModelSnapshot.transformedCoords,
																			function(
																					coord) {
																				return {
																					x : coord.x
																							+ obj.xOffset
																							- obj.shipView.minX,
																					y : coord.y
																							+ obj.yOffset
																							- obj.shipView.minY
																				}
																			})
														}
													});
									var requestData = {
										gameId : gameId,
										userId : userId,
										startConfigId : startConfigId,
										selectedPositions : data
									};
									$.ajax({
												url : window.baseUrl+"/game/addStartConfig",
												method : "POST",
												type : "POST",
												contentType : "application/json; charset=utf-8",
												data : JSON
														.stringify(requestData),
												dataType : "json",
												success : function(response) {
													$(".controls.start").addClass("hidden");
													$(".controls.waiting").removeClass("hidden");
													$(".disabling-overlay").addClass("on");
												}
											})
								});
			};

			var initBoardWithPrevData = function() {
				var data = JSON.parse($("#startConfigData").text());
				var ships = data.selectedPositions;
				for (var i = 0; i < ships.length; i++) {
					var shipModel = new BattleshipModel(
							ships[i].availableBattleshipId,
							ships[i].availableBattleshipPositions);
					for (var j = 0; j < ships[i].transformations.length; j++) {
						shipModel
								.applyTransform(Transforms[ships[i].transformations[j]]);
					}
					var shipView = new BattleshipView(shipModel);
					var newShipView = editingScreen.gameBoard.board.drawShip(
							shipView.getModel(), ships[i].xOffset,
							ships[i].yOffset, false);
					editingScreen.gameBoard.addShip(newShipView,
							ships[i].xOffset, ships[i].yOffset);
				}
			};

			var setupServerEventListener = function() {
				var stompClient = GameCommunications.openWSConnection(userId,	gameId);
				GameCommunications.subscribe(stompClient,	"/game/" + gameId + "/" + userId, function(message) {
					if (message.type == "END_CONFIG") {
						if (message.configSuccess) {
							window.location.replace(window.baseUrl+"/game/gameScreen?gameId="
											+ gameId + "&startConfigId=" + startConfigId);
						} else {
							window.alert("configuration time has expired");
							$("a.start-game").addClass("disabled");
							setTimeout(function(){
								window.location.replace(window.baseUrl+"/home");
							},2000);
						}
					}
				});
			};

			$(function() {
				gameTimer = new GameTimer({roundLength : 60});
				userId = Number($("#userId").text());
				gameId = Number($("#gameId").text());
				startConfigId = Number($("#startConfigId").text());
				editingScreen = gameEditing();
				bindStartButtonStateChangeHandler();
				bindStartButtonClickHandler();
				setupServerEventListener();
				gameTimer.start();
			})
		});