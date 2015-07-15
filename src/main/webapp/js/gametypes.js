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
		$shipsContainer.data( "size", { lines: n, columns: m });
	}

	//draw ships in shipsContainer using arrays with coordinates x,y; ship starts at offset
	//board has n lines and m columns
	//offsetUp - distance from top to ship(in cells -> .ship-cell)
	//offset - distance from left to ship(in cells -> .ship-cell)
	var drawShip = function(x, y, offsetX, offsetY, shipsContainer){

		var maxX = Math.max.apply(null, x);
		var minX = Math.min.apply(null, x); 
		var maxY = Math.max.apply(null, y);
		var minY = Math.min.apply(null, y); 
		
		var shipCellWidth = maxX-minX+1;
		var shipCellHeight = maxY-minY+1;
		
		var $divShip = $('<div></div>');
		$divShip.addClass('div-ship');
		
		if (shipsContainer.is('.transformation-container')){
			$divShip.addClass('transformShip');
		} else {
			$divShip.addClass('modelShip');
		}
		
		
		var $div = $($(shipsContainer).find('.div-row')[0]);
		var $cell = $($div.find(' > div')[0]);
		var cellHeight = $cell.outerHeight();
		var cellWidth = $cell.outerWidth();

		$divShip.outerWidth(cellWidth * shipCellWidth);
		$divShip.outerHeight(cellHeight * shipCellHeight);
		if ($(shipsContainer).is(".transformation-container")){
			$(shipsContainer).find(".transformation-grid").append($divShip);		
		} else {
			$(shipsContainer).append($divShip);			
		}


		for(var i = 0;i<shipCellHeight;i++){
			var $divRow = $('<div class="ship-row"></div>');
			for(var j = 0; j < shipCellWidth; j++){
				var $div = $('<div class="ship-cell"></div>');
				$div.outerWidth(cellWidth);
				$div.outerHeight(cellHeight);
				$divRow.append($div);
			}
			$divShip.append($divRow);
		}

		var left = (offsetX+minX)*cellWidth;
		$divShip.css('left',left + 'px');

		for(var i = 0; i < y.length; i++){
			var $div = $($divShip.find('.ship-row')[shipCellHeight-y[i]+minY-1]);
			var $cell = $($div.find(' > div')[x[i]-minX]);
			$cell.css('background-color','black');
		}

		var bottom = (offsetY+minY)*cellHeight;
		$divShip.css('bottom', bottom + 'px');

		$divShip.data("position",BattleshipPosition(x,y));
		
		return shipCellWidth+1;
	}

	//draws transfomations container whit m x n cells
	var drawTransformationContainer = function(m, n){
		var $container = $('.transformation-container');
		var $grid = $container.find(".transformation-grid");
		
		for(var i = 0; i < n ; i++){
			var $divRow = $('<div><div>');
			$divRow.addClass('div-row');
			for(var j = 0; j < m; j++){
				var $div = $('<div></div>');
				$divRow.append($div);
			}
			
			$grid.append($divRow);
		}
		
		$container.data( "size", { lines: n, columns: m});
		
	} 


	return{
		init : init,
		drawBoard : drawBoard,
		drawShipsBoard : drawShipsBoard,
		drawShip: drawShip,
		drawTransformationContainer : drawTransformationContainer
	}
};





var gameEditing = function(data){
	 
	var $selectedShip = null;
	var $draggingShip = null;
 	var board = generateBoard();
 	var $container =  $($('.board-container')[0])
	var $shipsContainer = $('.ships-container');
 	var $transformationContainer = $('.transformation-container');

 	
 	
 	
 	
 	//Send to server Game type information from the users
 	//Get From Server response
 	
 	board.init(9, 9, $container);
 	board.drawBoard();
 	board.drawShipsBoard(23, 4);
	
 	
 	/*	Draw ships in ships-container - June 25. 2015. added idIndex, so 
 	 * 	redraw function could be easier. Supposed BUGS could be available   */
	var offset = 0;
	
 	var x = [0,1,2,1];
	var y = [0,0,0,1];
 	offset += board.drawShip(x, y, offset, 0, $shipsContainer);

 	var x1 = [0,1,2,1,1];
 	var y1 = [1,0,1,1,2];
 	offset += board.drawShip(x1, y1, offset, 0, $shipsContainer);
 
 	var x2 = [0,0,0,1];
 	var y2 = [0,1,2,0];
 	offset += board.drawShip(x2, y2, offset, 0, $shipsContainer);

 	var x3 = [0,1,2];
 	var y3 = [0,0,0];
 	offset += board.drawShip(x3, y3, offset, 0, $shipsContainer);

 	var x4 = [0,1,2,3,4];
 	var y4 = [0,0,0,0,0];
 	offset += board.drawShip(x4, y4, offset, 0, $shipsContainer);
 	
	
};
	
	