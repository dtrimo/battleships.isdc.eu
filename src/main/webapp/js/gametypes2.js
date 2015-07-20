
var display = function(){
	if($(this).closest("li").find(".board-container").css("display") != "none"){
		$(this).closest("li").find(".board-container").css("display", "none");
	}
	else{
		$(this).closest("li").find(".board-container").css("display", "table");
	}
}

$(".expand").bind("click", display);