define(['jquery','board'],function($, Board){
	
	//A selection board contains a fixed list of battleships (BattleshipModel-s, in fact)
	//It supports displaying some or all these battleships in a fixed order and hiding or
	//showing individual ships.
	SelectionBoard = function(m, n, $container, shipClass, battleshipModels){
		this.board = new Board(m, n, $container, shipClass);
		this.battleshipModels = [];
		//to each model we attach an extra piece of information (the "visible" flag)
		for (var i=0;i<battleshipModels.length;i++){
			this.battleshipModels.push({
				model : battleshipModels[i],
				visible : true
			});
		}		
		this.battleshipViews = [];
	}

	SelectionBoard.prototype.drawBoard = function(){
		this.board.drawBoard();
		this.drawShips();
	}

	SelectionBoard.prototype.drawShips = function(){
		//Remove the previous ship views
		this.board.removeShips();
		this.battleshipViews.splice(0);
		//draw the new ship views
		this.crtDrawableOffset = 0;
		for (var i=0;i<this.battleshipModels.length;i++){
			if (this.battleshipModels[i].visible){
				var battleshipView = this.board.drawShip(this.battleshipModels[i].model, this.crtDrawableOffset, 0);				
				this.crtDrawableOffset += (battleshipView.shipCellWidth + 1);
				this.battleshipViews.push(battleshipView);				
			} else {
				this.battleshipViews.push(null);
			}
		}
	}
	
	SelectionBoard.prototype.showShip = function(battleshipModel){
		for (var i=0; i<this.battleshipModels.length; i++){
			if (this.battleshipModels[i].model === battleshipModel){
				this.battleshipModels[i].visible = true;
			}
		}
		this.drawShips();
	}
	
	SelectionBoard.prototype.hideShip = function(battleshipModel){
		for (var i=0; i<this.battleshipModels.length; i++){
			if (this.battleshipModels[i].model === battleshipModel){
				this.battleshipModels[i].visible = false;
			}
		}
		this.drawShips();
	}

	return SelectionBoard;
});
