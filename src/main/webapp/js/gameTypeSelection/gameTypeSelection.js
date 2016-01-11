requirejs.config({
	baseUrl : "js",
	paths : {
		'jquery' : 'lib/jquery-1.11.3',
		'battleshipModel' : 'board/battleshipModel',
		'battleshipView' : 'board/battleshipView',
		'coords' : 'board/coords',
		'transforms' : 'board/transforms',
		'board' : 'board/board',
		'selectionBoard' : 'boardConfiguration/selectionBoard'
	}
});

require(['jquery','selectionBoard', 'battleshipModel'],function($,SelectionBoard,BattleshipModel){
	
	var lastGameRequest = null;
	
	var drawContainer = function($container, availableBattleships){	
			new SelectionBoard(17,5,$container,"modelShip",$.map(availableBattleships,function(battleship){
				return new BattleshipModel(
						battleship.availableBattleshipModelId,
						battleship.battleshipModel.cells);
			})).drawBoard();
	}
	
	var drawGameTypes = function(gametypesList){
		var $gameTypeListContainer = $("ul.types");
		for (var i=0;i<gametypesList.length;i++){
			var gameType = gametypesList[i];
			var $li = $("<li></li>");
			$li.append('<span class="gametypeId" style="display:none">'+gameType.id+'</span>');
			$li.append('<span class="gameName">'+gameType.name+'</span>');
			$li.append("<span class='description'>"+gameType.shortDescription+"</span>");
			var buttonsControl = "<div class='controls clearfix'>";
			buttonsControl += "<a class='button submit choose-type'>Play</a>";
			buttonsControl += "<a class='button expand'>Show available battleships</a>";
			buttonsControl += "</div>";
			$li.append(buttonsControl);
			var $boardContainer = $("<div class='board-container'></div>");
			$li.append($boardContainer);
			$li.appendTo($gameTypeListContainer);
			drawContainer($boardContainer,gameType.battleships);
		}
	}
	
	var toggleGameTypeDetailDisplay = function(){
		if($(this).closest("li").find(".board-container").css("display") != "none"){
			$(this).closest("li").find(".board-container").css("display", "none");
			$(this).text("Show available battleships");
		} else{
			$(this).closest("li").find(".board-container").css("display", "table");
			$(this).text("Hide available battleships");
		}
	};

	var showLoadingScreen = function(){
		$(".loading").css("display", "inline-block");
		$(".cancel").css("display", "inline-block");
		$("ul.types").css("opacity", "0.05");
		$("div.available-game-types div.box-title").css("opacity", "0.05");
		$("div.available-game-types a.cancel-game-request").css("top",
				($("div.available-game-types").height()/2+100)+"px");
	};

	var hideLoadingScreen = function(){
		$(".loading").css("display", "none");
		$(".cancel").css("display", "none");
		$("ul.types").css("opacity", "1");
		$("div.available-game-types div.box-title").css("opacity", "1");
	};
	
	var cancelLastGameRequest = function(){
		if (lastGameRequest==null){
			return;
		}
		$.ajax({
			method : "post",
			url : window.baseUrl+"/gametypes/delete",
			data : {
				gametypeId : lastGameRequest.gametypeId
			},		
			success : function(response){
				hideLoadingScreen();
				lastGameRequest = null;
			}
		});
	}
	
	var bindGameTypeSubmit = function(){
		$("a.submit.choose-type").on("click", function(event){
			event.preventDefault();
			showLoadingScreen();
			lastGameRequest = {
					gametypeId : $(this).closest("li").find(".gametypeId").text()
			};
			$.ajax({
				method : "post",
				url : window.baseUrl+"/gametypes",
				data : {
					gametypeId : lastGameRequest.gametypeId
				},		
				success : function(response){
					hideLoadingScreen();
					if(response.errorMsg!=null && response.errorMsg.length > 0){
						if (response.errorMsg == "INTERRUPTED_BY_REQUEST"){
							console.log("Game request successfully canceled");
						} else {
							alert(response.errorMsg);							
						}
					}	else{
						top.location = window.baseUrl+"/game/startboard?gameId="+response.gameId+"&gameRole="+response.gameRole+"&startConfigId="+response.startConfigId;
					}
				},
				error : function(error){
					alert(error);
				}
			});
		});
	}
	
	$(function(){	
		$.ajax({
			url : "game/types/list",
			success : function(responseData){
				drawGameTypes(responseData);
				bindGameTypeSubmit();
				$("body").on("click",".expand", toggleGameTypeDetailDisplay);
				$("a.cancel-game-request").on("click", cancelLastGameRequest);				
			}
		});
	});

});