//shipClass is the class assigned to ships drawn on this board
var Board = function(m, n, $container, shipClass){		
	this.m=m;
	this.n=n;
	this.$container = $($container);
	this.shipClass = shipClass;
};

//board has n lines and m columns
Board.prototype.drawBoard = function(){			
	for(var i = 0; i < this.n; i++){
		var $divRow = $('<div><div>');
		$divRow.addClass('div-row');
		
		//TODO: Something's wrong here. I had to limit j
		//to m - 1 so it draws the correct number of collumns
		for(var j = 0; j < this.m - 1; j++){
			var $div = $('<div></div>');
			$divRow.append($div);
		}
		this.$container.append($divRow);
	}
	this.$container.data( "size", { lines: this.n, columns: this.m });
};

//draw ships in shipsContainer using arrays with coordinates x,y; ship starts at offset
//board has n lines and m columns
//offsetUp - distance from top to ship(in cells -> .ship-cell)
//offset - distance from left to ship(in cells -> .ship-cell)
Board.prototype.drawShip = function(x, y, offsetX, offsetY){		
	var maxX = Math.max.apply(null, x);
	var minX = Math.min.apply(null, x); 
	var maxY = Math.max.apply(null, y);
	var minY = Math.min.apply(null, y); 
	
	var shipCellWidth = maxX-minX+1;
	var shipCellHeight = maxY-minY+1;
	
	var $divShip = $('<div></div>');
	$divShip.addClass('div-ship');
		
	//find first row of the board
	var $div = $(this.$container.find('.div-row')[0]);
	//find first cell in the first row
	var $cell = $($div.find(' > div')[0]);
	var cellHeight = $cell.outerHeight();
	var cellWidth = $cell.outerWidth();
	
	$divShip.outerWidth(cellWidth * shipCellWidth);
	$divShip.outerHeight(cellHeight * shipCellHeight);	
	if (this.shipClass){
		$divShip.addClass(this.shipClass);
	}
	this.$container.append($divShip);
	
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
		$cell.css('background-color','rgb(39, 108, 56)');
	}
	
	var bottom = (offsetY+minY)*cellHeight;
	$divShip.css('bottom', bottom + 'px');
	
	//var position = {x:x, y:y};//BattleshipPosition(x,y);
	//$divShip.data("position",position);
	
	return 	{
		$container : $divShip,
		//position : position,
		width : shipCellWidth,
		height : shipCellHeight
	}
};

Board.prototype.removeShips = function(){
	this.$container.find(".div-ship").each(function(){
		$(this).remove();
	});
};