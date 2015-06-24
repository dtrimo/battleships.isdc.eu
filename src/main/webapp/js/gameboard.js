var generateBoard = function(){
	var mGameBoard;
	var nGameBoard;
	var $container;

	var init = function(lines,columns,tableContainer){
		nGameBoard = lines;
		mGameBoard = columns;
		$container = $(tableContainer); 
	}

	var drawBoard = function(){
		for(var i = 0; i < nGameBoard ; i++){
			var $divRow = $('<div><div>');
			$divRow.addClass('div-row');
			for(var j = 0; j < mGameBoard; j++){
				var $div = $('<div></div>');
				$divRow.append($div);
			}
			$container.append($divRow);
		}

	}
 	
 	//board has n lines and m columns
	var drawShipsBoard = function(m, n){
		var $shipsContainer = $('.ships-container');

		for(var i = 0; i < n; i++){
			var $divRow = $('<div><div>');
			$divRow.addClass('div-row');
			for(var j = 0; j < m; j++){
				var $div = $('<div></div>');
				$divRow.append($div);
			}
			$shipsContainer.append($divRow);
		}
	}

	//draw ships in shipsContainer using arrays with coordinates x,y; ship starts at offset
	//board has n lines and m columns
	//offsetUp - distance from top to ship(in cells -> .ship-cell)
	//offset - distance from left to ship(in cells -> .ship-cell)
	var drawShip = function(x, y, offset, offsetUp, shipsContainer){

		var maxX = Math.max.apply(null, x);
		var maxY = Math.max.apply(null, y);
		var xAux = [];
		for (var i = x.length - 1; i >= 0; i--) {
			xAux[i] = x[i] + offset;
		};

		var maxXAux = Math.max.apply(null, xAux);
		
		var $divShip = $('<div></div>');
		$divShip.addClass('div-ship');
		
		var $div = $($(shipsContainer).find('.div-row')[0]);
		var $cell = $($div.find(' > div')[0]);
		var cellHeight = $cell.outerHeight();
		var cellWidth = $cell.outerWidth();

		$divShip.outerWidth(cellWidth * (maxX+1));
		$divShip.outerHeight(cellHeight * (maxY+1));
		$(shipsContainer).append($divShip);


		for(var i = 0; i < (maxY + 1); i++){
			var $divRow = $('<div></div>');
			$divRow.addClass('ship-row');
			for(var j = 0; j < (maxXAux + 1); j++){
				var $div = $('<div></div>');
				$div.addClass('ship-cell');
				$div.outerWidth(cellWidth);
				$div.outerHeight(cellHeight);
				$divRow.append($div);
			}
			$divShip.append($divRow);
		}

		var left = offset*cellWidth;
		$divShip.css('left',left + 'px');


		for(var i = 0; i < y.length; i++){
			var $div = $($divShip.find('.ship-row')[maxY - y[i]]);
			var $cell = $($div.find(' > div')[x[i]]);
			$cell.css('background-color','black');
		}
		
		offset = maxXAux + 3;

		if(offsetUp > 0){
			var top = offsetUp*cellHeight;
			$divShip.css('top', top + 'px');
		}else{
			var n = $(shipsContainer).data('size').lines;
			n--;
			var top = (n - maxY)*cellHeight;
			$divShip.css('top', top + 'px');
		}

		return offset;
	}

	//draws transfomations container whit m x n cells
	var drawTransformationContainer = function(m, n){
		var $container = $('.transformation-container');
		

		for(var i = 0; i < n ; i++){
			var $divRow = $('<div><div>');
			$divRow.addClass('div-row');
			for(var j = 0; j < m; j++){
				var $div = $('<div></div>');
				$divRow.append($div);
			}
			$container.append($divRow);
		}
	} 


	return{
		init : init,
		drawBoard : drawBoard,
		drawShipsBoard : drawShipsBoard,
		drawShip: drawShip,
		drawTransformationContainer : drawTransformationContainer
	}
}

 $(function(){
 	var board = generateBoard();
 	var $container =  $($('.board-container')[0])
	var $shipsContainer = $('.ships-container');

 	board.init(9, 9, $container);
 	board.drawBoard();
 	board.drawShipsBoard(23, 4);
	$shipsContainer.data( "size", { lines: 4, columns: 23 });
 	
 	//draw ships in ships-container
 	var x = [0,1,2,1];
	var y = [0,0,0,1];
	var offset = 0;
 	offset = board.drawShip(x, y, offset, 0, $shipsContainer);

 	var x1 = [0,1,2,1,1];
 	var y1 = [1,0,1,1,2];
 	offset = board.drawShip(x1, y1, offset, 0, $shipsContainer);
 
 	var x2 = [0,0,0,1];
 	var y2 = [0,1,2,0];
 	offset = board.drawShip(x2, y2, offset, 0, $shipsContainer);

 	var x3 = [0,1,2];
 	var y3 = [0,0,0];
 	offset = board.drawShip(x3, y3, offset, 0, $shipsContainer);

 	var x4 = [0,1,2,3,4];
 	var y4 = [0,0,0,0,0];
 	offset = board.drawShip(x4, y4, offset, 0, $shipsContainer);

 	board.drawTransformationContainer(14, 14);
 	$('.transformation-container').data( "size", { lines: 14, columns: 14});

 	//draw ship in transformation container
 	var x5 = [0,1,2,1];
	var y5 = [0,0,0,1];
	var offset = 6;
	var offsetUp = 7;
	var $transformationContainer = $('.transformation-container');
 	board.drawShip(x5, y5, offset, offsetUp, $transformationContainer);

 	//d&d from ships container to board container.
 	$('.div-ship').draggable({
 		cursor: 'move',
 		revert: 'invalid' 			
	});

 	$('.board-container').droppable({
 		revert: false,
 		tolerance:'fit',
		drop: function(event, ui){
			var d1 = $(".board-container");
			var d2 = $(".ships-container");
			var p1 = d1.offset();
			var p2 = d2.offset();
			var rel = { left: p2.left-p1.left, top: p2.top-p1.top };

			//position where dropped
    		var dropPosition = $(ui.draggable).position();
    		var left = dropPosition.left + rel.left;

    		if(dropPosition.top % 40 != 0){
    			dropPosition.top = Math.round(dropPosition.top / 40) * 40;
    		}

    		$(ui.draggable).css('top', dropPosition.top + 'px');

    		if(left % 40 != 0){
    			left = Math.round(left / 40) * 40;
    		}

    		left = left - rel.left;
    		$(ui.draggable).css('left', left + 'px');
		}
 	});

 	// Stuff to do:
 	//  -validate if two ships overlay in the board container
 	//  -when clicked ships from ships-container a similar ship should be drawn in the transformation container
 	//  -add events when buttons are clicked
 	//  -drag and drop should work like that: drag from transformation container, drop into board container. 

});