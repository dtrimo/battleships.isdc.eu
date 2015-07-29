/**
 * 
 * @author Dragos.Florea
 * javascript functions that power the gametype selection page
 */

(function($){
	
	var drawContainer = function($container, availableBattleships){	
			var board = new Board(20,5,$container);
			board.drawBoard();

			var offset = 0;
			for(var k = 0; k < availableBattleships.length; k++){
				offset += board.drawShip(availableBattleships[k].x, availableBattleships[k].y, offset, 0).width+1;				
			}		
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

	$(function(){		
		drawGameTypes();
		$("body").on("click",".expand", display);
	});

})(jQuery);


var showLoadingScreen = function(){
	$(".loading").css("display", "inline-block");
	$(".cancel").css("display", "inline-block");
};

var hideLoadingScreen = function(){
	$(".loading").css("display", "none");
	$(".cancel").css("display", "none");
};

$(document).ready(function() {
var $form = $("form.playform");
$form.on("submit", function(event){
	event.preventDefault();
	showLoadingScreen();
	$.ajax({
		method : "post",
		url: "http://localhost:8080/battleships/gametypes",
		data : {
			gametypeId : $(this).closest("li").find(".gametypeId").text()
		},		
		success : function(response){
			hideLoadingScreen();
			if(response.errorMsg!=null && response.errorMsg.length > 0){
				alert(response.errorMsg);
			}
			else{
				top.location = "http://localhost:8080/battleships/game?gameId="+response.gameId+"&gameRole="+response.gameRole
			}
		},
		error : function(error){
			   alert(error)
			}
		
		
	});
	
});
})

$(document).ready(function(){
	$(".cancel").on("click", hideLoadingScreen);
})	
	
		

