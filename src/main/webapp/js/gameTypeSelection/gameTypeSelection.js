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
	
	var drawContainer = function($container, availableBattleships){	
			new SelectionBoard(20,5,$container,"",$.map(availableBattleships,function(battleship){
				return new BattleshipModel(
						battleship.availableBattleshipModelId,
						battleship.battleshipModel.cells);
			})).drawBoard();
	}
	
	var drawGameTypes = function(){
		var $gameTypeListContainer = $("ul.types");
		var gametypesList = $.parseJSON($("#gameConfigData").text());
		for (var i=0;i<gametypesList.length;i++){
			var gameType = gametypesList[i];
			var $li = $("<li></li>");
			$li.append('<span class="gametypeId" style="display:none">'+gameType.id+'</span>');
			$li.append("<span class='description'>"+gameType.shortDescription+"</span>");
			$li.append("<form class='playform'> <button type='submit' class='play'>Play this!</button></form>");
			$li.append("<button type='button' class='expand'>Show available battleships</button>");
			var $boardContainer = $("<div class='board-container'></div>");
			$li.append($boardContainer);
			$li.appendTo($gameTypeListContainer);
			drawContainer($boardContainer,gameType.battleships);
		}
	}
	
	var display = function(){
		if($(this).closest("li").find(".board-container").css("display") != "none"){
			$(this).closest("li").find(".board-container").css("display", "none");
		}
		else{
			$(this).closest("li").find(".board-container").css("display", "table");
		}
	}

	var showLoadingScreen = function(){
		$(".loading").css("display", "inline-block");
		$(".cancel").css("display", "inline-block");
	};

	var hideLoadingScreen = function(){
		$(".loading").css("display", "none");
		$(".cancel").css("display", "none");
	};
	
	var bindGameTypeSubmit = function(){
		$("form.playform").on("submit", function(event){
			event.preventDefault();
			showLoadingScreen();
			debugger;
			$.ajax({
				method : "post",
				data : {
					gametypeId : $(this).closest("li").find(".gametypeId").text()
				},		
				success : function(response){
					hideLoadingScreen();
					if(response.errorMsg!=null && response.errorMsg.length > 0){
						alert(response.errorMsg);
					}
					else{
						top.location = "http://localhost:8080/battleships/game?gameId="+response.gameId+"&gameRole="+response.gameRole+"&startConfigId="+response.startConfigId;
					}
				},
				error : function(error){
					
					alert(error);
				}
			});
		});
	}
	
	$(function(){		
		drawGameTypes();
		bindGameTypeSubmit();
		$("body").on("click",".expand", display);
		$(".cancel").on("click", hideLoadingScreen);
	});

});