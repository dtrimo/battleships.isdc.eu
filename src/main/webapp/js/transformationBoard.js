(function($) {
	TransformationBoard = function(m, n, $container, shipClass, gameBoard) {
		this.m = m;
		this.n = n;
		this.$container = $($container);
		this.shipClass = shipClass;
		this.board = new Board(m, n, $container, shipClass);
		this.gameBoard = gameBoard;
		this.ship = null;
		bindDraggableShipHandler(this);
	}

	TransformationBoard.prototype.drawBoard = function() {
		this.board.drawBoard();
	}

	TransformationBoard.prototype.drawShip = function(x, y, offsetX, offsetY) {
		var ship = this.board.drawShip(x, y, offsetX, offsetY);
		this.ship = ship;
		return ship;
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
				start : function(event, ui) {
					//TODO: Dude, this is totaly not okay
					//$(document).trigger("selectedShipChanged",transformationBoard.ship.$container);
				}
			})
		});
	}
})(jQuery);
