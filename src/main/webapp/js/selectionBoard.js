(function($){
	SelectionBoard = function(m, n, $container, shipClass, transformBoard){
		this.m=m;
		this.n=n;
		this.$container=$($container);
		this.shipClass=shipClass;
		this.board = new Board(m, n, $container, shipClass);
		this.transformBoard = transformBoard;
		this.ships = [];
		bindShipClickHandler(this);
	}

	SelectionBoard.prototype.drawBoard = function(){
		this.board.drawBoard();
	}

	SelectionBoard.prototype.drawShip = function(x, y, offsetX, offsetY){
		var ship = this.board.drawShip(x,y,offsetX, offsetY);
		this.ships.push(ship);
		return ship;
	}

	SelectionBoard.prototype.removeShips = function() {
		this.board.removeShips();
		var ships = this.ships;
		this.ships = [];
		return ships;
	}

	SelectionBoard.prototype.getShip = function(shipSelector){
		for (var i=0;i<this.ships.length;i++){
			if (this.ships[i].$container.is($(shipSelector))){
				return this.ships[i];
			}
		}
		return null;
	}

	SelectionBoard.prototype.removeShip = function(shipSelector){
		var ship = this.getShip(shipSelector);
		if (ship!=null){
			ship.$container.remove();
			this.ships.splice(this.ships.indexOf(ship),1);
		}
		
		return ship;
	}

	var bindShipClickHandler = function(selectionBoard){
		selectionBoard.$container.on("click","."+selectionBoard.shipClass,function(){
			selectionBoard.transformBoard.removeShips();
			var $ship = $(this);	
			var xCoords = $ship.data("position").getXCoords();
			var yCoords = $ship.data("position").getYCoords();
			//TODO: replace with something like transformBoard.drawCentered(xCoords, yCoords);
			selectionBoard.transformBoard.drawShip(xCoords, yCoords, 6, 6);
			$(document).trigger("selectedShipChanged",$(this));
		});
	}

})(jQuery);
