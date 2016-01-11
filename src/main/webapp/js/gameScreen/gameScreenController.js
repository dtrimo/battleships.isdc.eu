define(['jquery','gameBoard'],function($,GameBoard){
	
	var Controller = function(gameBoard, opponentGameBoard){
		this.gameBoard = gameBoard;
		this.opponentGameBoard = opponentGameBoard;
		this.selectedCells = {};
		this.selectionAllowed = false;
		this.myHits = 0;
		this.opponentHits = 0;
		this.gameEnded = false;
	};
	
	Controller.prototype.init = function(gameTimer, stompClient){
		this.gameTimer = gameTimer;
		this.stompClient = stompClient;
		bindClick(this.gameBoard, this);
		bindRoundChange(this);
	}
	
	var bindClick = function(gameBoard, controller){
		if (controller.gameEnded){
			return;
		}
		gameBoard.board.$container.on("click",".board-cell:not(.pending,.hit,.miss)", function(event){
			if (!controller.selectionAllowed){
				return;
			}
			var round = controller.gameTimer.currentRound;
			var $cell = $(this);
			var coordinates = $cell.data("coordinates");
			controller.selectedCells[round] = {
				$cell : $cell,
				round : round,
				coordinates : coordinates,
				hit : false
			};
			$cell.addClass("pending");
			controller.selectionAllowed = false;
			
			var stompClient = controller.stompClient;
			stompClient.client.send("/gameEvents/userFired", {}, JSON.stringify({
				round : round,
				x : coordinates.x,
				y : coordinates.y,
				timestamp : new Date().getTime(),
				userId : stompClient.userId,
				gameId : stompClient.gameId,
				startConfigId : stompClient.startConfigId
			}));
			
		});
	};
	
	var bindRoundChange = function(controller){
		$(document).on("roundChange", function(){
			controller.selectionAllowed = true;
		});
	};
	
	Controller.prototype.processFireResult = function(fireResultMessage){
		var round = fireResultMessage.round;
		var hit = fireResultMessage.hit;
		var $cell = this.selectedCells[round].$cell;
		$cell.removeClass("pending");
		$cell.addClass(hit ? "hit" : "miss");
		this.selectedCells[round].hit = hit;
		this.myHits += hit ? 1 : 0;
		$(".my-hit-count").text(this.myHits);
	};
	
	Controller.prototype.processEndRound = function(endRoundMessage){
		if (endRoundMessage.enemyShot!=null){
			var x = endRoundMessage.enemyShot.x;
			var y = endRoundMessage.enemyShot.y;
			var shipView = this.opponentGameBoard.getShipViewForCell(x, y);
			var hit = shipView != null;
			var $clonedCell = this.opponentGameBoard.board.coverBoardCellDiv(x,y);
			$clonedCell.addClass("cloned-overlay");
			$clonedCell.addClass(hit ? "hit" : "miss");			
			this.opponentHits+=hit ? 1 : 0;
			$(".opponent-hit-count").text(this.opponentHits);
		}
		if (endRoundMessage.gameEnded){
			this.gameEnded = true;
			if (endRoundMessage.won){
				alert("Congratulations! You won!");
			} else {
				alert("Sorry! You lost!");
			}
			setTimeout(function(){
				window.location.href = "home";
			},2000);
		}
	}
	
	return Controller;
	
});