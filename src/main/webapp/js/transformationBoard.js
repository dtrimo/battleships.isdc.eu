(function($) {
	TransformationBoard = function(m, n, $container, shipClass) {
		this.m = m;
		this.n = n;
		this.$container = $($container);
		this.shipClass = shipClass;
		this.board = new Board(m, n, $container, shipClass);
		this.ship = null;
		bindDraggableShipHandler(this);
	}

	TransformationBoard.prototype.drawBoard = function() {
		this.board.drawBoard();
		this.drawAxis();
	}

	TransformationBoard.prototype.drawShip = function(x, y) {
		var ship = this.board.drawShip(x, y, this.m / 2, this.n / 2);
		this.ship = ship;
		return ship;
	}
	
	TransformationBoard.prototype.drawAxis = function() {
		var transformBoard = this;
		var rows = [];
		rows = this.$container.find($('.div-row'));
		var numRows = rows.length;
		for (var i = 0; i < numRows; i++) {
			$(rows[i]).find('div').each(function(index) {
				if (i + 1 == transformBoard.n / 2)
					$(this).css('border-bottom', '2px solid orange');
				if (i + 1 == transformBoard.n / 2 + 1)
					$(this).css('border-top', '2px solid orange');
				if (index + 1 == transformBoard.m / 2)
					$(this).css('border-right', '2px solid orange');
				if (index + 1 == transformBoard.m / 2 + 1)
					$(this).css('border-left', '2px solid orange');
			});
		}
	}

	TransformationBoard.prototype.getShip = function() {
		return this.ship;
	}

	TransformationBoard.prototype.removeShip = function() {
		var ship = this.ship;
		if (ship != null) {
			this.board.removeShips();
			this.ship = null;
		}
		return ship;
	}

	var bindDraggableShipHandler = function(transformationBoard) {
		transformationBoard.$container.on('mouseover', function() {
			if (transformationBoard.ship != null) 
				transformationBoard.ship.$container.draggable({
				cursor : 'move',
				revert : 'invalid',
			})
		});
	}
})(jQuery);
