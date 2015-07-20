/*var draw = function(){
	var draw = $(this).closest("li").find(".div-row").length == 0;
	if(draw == true){
		var $container =  $(this).closest("li").find('.board-container');
		var board = new Board(20,5,$container);

		board.drawBoard();

		var offset = 0;
		var x = [0,1,2,1];
		var y = [0,0,0,1];
		offset += board.drawShip(x, y, offset, 0, $container).width+1;
		
		var x = [0,1,2,1];
		var y = [0,0,0,1];
		offset += board.drawShip(x, y, offset, 0, $container).width+1;
				
		var x = [0,1,2,1];
		var y = [0,0,0,1];
		offset += board.drawShip(x, y, offset, 0, $container).width+1;
		
	}
}
*/


var draw = function(arr, cont){
	
	var container = $("#" + cont);
	//var container = $("#c0");
	var draw = container.find(".div-row").length == 0;
	if(draw == true){
		var board = new Board(20,5,container);

		board.drawBoard();
		var offset = 0;
		for(var i = 0; i < arr.length; i++){
			var x = [];
			var y = [];
			var obj = arr[i];
			for(var key in obj){
				if(key == "x")
					x = obj[key];
				if(key == "y")
					y = obj[key];
			}
			offset += board.drawShip(x, y, offset, 0, container).width+1;
		}
	}
}



