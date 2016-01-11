define([ 'jquery', 'battleshipModel', 'battleshipView'], function($, BattleshipModel, BattleshipView) {
	// shipClass is the class assigned to ships drawn on this board
	// m and n are the dimensions of the board.
	// $container is a jquery object wrapping the html container in which the board will be drawn
	var Board = function(m, n, $container, shipClass) {
		this.width = m;
		this.height = n;
		this.$container = $($container);
		this.shipClass = shipClass;
	};

	// board has n lines and m columns
	Board.prototype.drawBoard = function() {
		for (var i = 0; i < this.height; i++) {
			var $divRow = $('<div></div>');
			$divRow.addClass('board-row');

			for (var j = 0; j < this.width; j++) {
				var $divCell = $('<div></div>');
				$divCell.addClass('board-cell');
				$divCell.data("coordinates",{
					x:j,
					y:this.height-i-1
				});
				$divRow.append($divCell);
			}
			this.$container.append($divRow);
		}
		this.$container.data("size", {
			lines : this.height,
			columns : this.width
		});
	};

	// offsetX and offsetY are relative to the bottom-left corner of the board
	// (like regular cartesian coordinates)
	// When normalized == true, the ship is drawn so that it is in the first quadrant
	// determined by the lines x=offsetX and y=offsetY (a translation if performed). This
	// is the default.
	Board.prototype.drawShip = function(battleshipModel, offsetX, offsetY, normalized) {
		if (normalized === undefined){
			normalized = true;
		}
		// find first cell in the first row
		var $cell = $(this.$container.find('.board-cell')[0]);
		var cellHeight = $cell.outerHeight();
		var cellWidth = $cell.outerWidth();
		
		var battleshipView =  new BattleshipView(battleshipModel);
		var $divShip = battleshipView.render(cellHeight,cellWidth, normalized);
		$divShip.addClass(this.shipClass);

		var finalOffsetX = normalized ? battleshipView.minX + offsetX : offsetX;
		var finalOffsetY = normalized ? battleshipView.minY + offsetY : offsetY;
		$divShip.css('left', finalOffsetX * cellWidth + 'px');
		$divShip.css('bottom', finalOffsetY * cellHeight + 'px');
		$divShip.appendTo(this.$container);

		return battleshipView;
	};

	Board.prototype.removeShips = function() {
		this.$container.find("."+this.shipClass).each(function() {
			$(this).data("shipView").destroy();
		});
	};
	
	Board.prototype.getCellWidth = function(){
		return $(this.$container.find('.board-cell')[0]).outerWidth();
	}
	
	Board.prototype.getCellHeight = function(){
		return $(this.$container.find('.board-cell')[0]).outerHeight();
	}

	Board.prototype.getBoardCellDiv = function(x, y){
		return $(this.$container.children(":nth-child("+(this.height-y)+")").children(":nth-child("+(x+1)+")"));
	}
	
	Board.prototype.coverBoardCellDiv = function(x, y, zIndex){
		var $coveredCell = this.getBoardCellDiv(x,y);
		var $newCell = $coveredCell.clone();
		
		var $cell = $(this.$container.find('.board-cell')[0]);
		var cellHeight = $cell.outerHeight();
		var cellWidth = $cell.outerWidth();
		
		$newCell.css('left', x * cellWidth + 'px');
		$newCell.css('bottom', y * cellHeight + 'px');
		$newCell.css("z-Index", zIndex === undefined ? 1000 : zIndex);
		$newCell.appendTo(this.$container);
		return $newCell;
	}
	
	return Board;
});
