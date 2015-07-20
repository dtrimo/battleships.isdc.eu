
(function($){
	SelectionBoard = function(m, n, $container, shipClass, transformBoard){
		this.m=m;
		this.n=n;
		this.$container=$($container);
		this.shipClass=shipClass;
		this.board = new Board(m, n, $container, shipClass);
		this.transformBoard = transformBoard;
		this.ships = [];
		this.crtDrawableOffset = 0;
		bindShipClickHandler(this);
	}

	SelectionBoard.prototype.drawBoard = function(){
		this.board.drawBoard();
	}

	SelectionBoard.prototype.drawShip = function(x, y){
		var ship = this.board.drawShip(x, y, this.crtDrawableOffset, 0);		
		this.crtDrawableOffset += (ship.width + 1);
		this.ships.push(ship);
		return ship;
	}

	SelectionBoard.prototype.reloadShips = function() {
		this.crtDrawableOffset = 0;
		this.board.removeShips();
		var updatedShips = [];
		var length = this.ships.length;
		for (var i = 0; i < length; i++) {
			var xCoords = this.ships[i].position.getXCoords();
			var yCoords = this.ships[i].position.getYCoords();
			updatedShips.push(this.drawShip(xCoords, yCoords));
		}
		this.ships = updatedShips;
	}
	
	SelectionBoard.prototype.removeShips = function() {
		this.board.removeShips();
		var ships = this.ships;
		this.ships = [];
		this.crtDrawableOffset = 0;
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
		this.reloadShips();
		
		return ship;
	}
	
	var bindShipClickHandler = function(selectionBoard){
		selectionBoard.$container.on("click","."+selectionBoard.shipClass,function(){
			var transformShip = selectionBoard.transformBoard.getShip();
			if (transformShip != null) {						
				selectionBoard.ships.push(transformShip);
				selectionBoard.transformBoard.removeShip();
			}

			var ship = selectionBoard.getShip($(this));
			var xCoordsTransformShip = ship.position.getXCoords();
			var yCoordsTransformShip = ship.position.getYCoords();
			
			selectionBoard.transformBoard.drawShip(xCoordsTransformShip, yCoordsTransformShip);
			
			selectionBoard.removeShip($(this));
		});
	}

})(jQuery);
