define(['jquery','board','jqueryUI','battleshipView'],function($, Board, BattleshipView){

	var GameBoard = function(m, n, $container, shipClass) {
		this.board = new Board(m, n, $container, shipClass);
		//ship is an array of BattleshipView objects
		this.containedBattleshipViews = [];
	}
	
	GameBoard.prototype.drawBoard = function() {
		this.board.drawBoard();
	}
	
	GameBoard.prototype.addShip = function(battleshipView,xOffset,yOffset){
		var shipView = this.getShip(battleshipView);
		if (shipView == null){
			this.containedBattleshipViews.push({
				shipView : battleshipView,
				xOffset : xOffset,
				yOffset : yOffset
			});
		} else {
			shipView.shipView = battleshipView;
			shipView.xOffset = xOffset;
			shipView.yOffset = yOffset;
		}
	}
	
	GameBoard.prototype.hasShip = function(battleshipView){
		for (var i=0;i<this.containedBattleshipViews;i++){
			if (this.containedBattleshipViews[i].shipView.getModel() == battleshipView.getModel()){
				return true;
			}
		}
		return false;
	}
	
	GameBoard.prototype.getShip = function(battleshipView){
		for (var i=0;i<this.containedBattleshipViews.length;i++){
			if (this.containedBattleshipViews[i].shipView.getModel() == battleshipView.getModel()){
				return this.containedBattleshipViews[i];
			}
		}
		return null;
	}
	
	GameBoard.prototype.removeShip = function(battleshipView){
		var index = -1;
		for (var i=0;i<this.containedBattleshipViews.length;i++){
			if (this.containedBattleshipViews[i].shipView.getModel() == battleshipView.getModel()){
				index = i;
				break;
			}
		}
		if (index >= 0){
			var battleshipView = this.containedBattleshipViews[index].shipView;
			battleshipView.destroy();
			this.containedBattleshipViews.splice(index,1);
			return battleshipView;
		}
		return null;
	}
	
	GameBoard.prototype.getContainedBattleshipViews = function(){
		return this.containedBattleshipViews;
	}
	
	var occupiesCell = function(x, y, battleshipView, shipOffsetX, shipOffsetY){
		var shipCoords = battleshipView.getModelSnapshot().getTransformedCoords();
		var minX = battleshipView.minX;
		var minY = battleshipView.minY;
		for (var i=0;i<shipCoords.length;i++){
			if (shipCoords[i].x+shipOffsetX - (battleshipView.normalized ? 0 : minX)==x &&
				shipCoords[i].y+shipOffsetY - (battleshipView.normalized ? 0 : minY)==y){
				return true;
			}
		}
		return false;
	}
	
	//x and y are measured from the bottom-left corner
	GameBoard.prototype.getOccupiedCells = function(){
		var occupiedCells = [];
		for (var i=0;i<this.board.width;i++){
			for (var j=0;j<this.board.height;j++){
				for (k=0;k<this.containedBattleshipViews.length;k++){
					if (occupiesCell(i,j,this.containedBattleshipViews[k].shipView,
							this.containedBattleshipViews[k].xOffset,
							this.containedBattleshipViews[k].yOffset)){
						occupiedCells.push({
							x: i,
							y: j
						});
					}
				}
			}
		}
		return occupiedCells;
	}
	
	GameBoard.prototype.getOccupiedCellsForShip = function(battleshipView, offsetX, offsetY){
		var occupiedCoords = [];
		var shipCoords = battleshipView.getModelSnapshot().getTransformedCoords();
		var minX = battleshipView.minX;
		var minY = battleshipView.minY;
		for (var i=0;i<shipCoords.length;i++){
			occupiedCoords.push({
				x : shipCoords[i].x + offsetX - minX,
				y : shipCoords[i].y + offsetY - minY
			});
		}
		return occupiedCoords;
	}
	
	GameBoard.prototype.battleshipsIntersect = function(battleshipView1, offsetX1, offsetY1, battleshipView2, offsetX2, offsetY2){
		var occupiedCoords1 = this.getOccupiedCellsForShip(battleshipView1, offsetX1, offsetY1);
		var occupiedCoords2 = this.getOccupiedCellsForShip(battleshipView2, offsetX2, offsetY2);
		for (var i=0; i<occupiedCoords1.length; i++){
			for (var j=0; j<occupiedCoords2.length; j++){
				if (occupiedCoords1[i].x==occupiedCoords2[j].x && occupiedCoords1[i].y==occupiedCoords2[j].y){
					return true;
				}
			}
		}
		return false;
	}
	
	GameBoard.prototype.getShipViewForCell = function (x,y){
		for (k=0;k<this.containedBattleshipViews.length;k++){
			if (occupiesCell(x,y,this.containedBattleshipViews[k].shipView,
					this.containedBattleshipViews[k].xOffset,
					this.containedBattleshipViews[k].yOffset)){
				return this.containedBattleshipViews[k].shipView;
			}
		}
		return null;
	}
	
	GameBoard.prototype.isShipAdditionValid = function(battleshipView, offsetX, offsetY){
		for (var i=0;i<this.containedBattleshipViews.length;i++){
			if (this.containedBattleshipViews[i].shipView.getModel() == battleshipView.getModel()){
				continue;
			}
			if (this.battleshipsIntersect(this.containedBattleshipViews[i].shipView,
					this.containedBattleshipViews[i].xOffset,
					this.containedBattleshipViews[i].yOffset,
					battleshipView,offsetX,offsetY)){
				return false;
			}
		}
		return true;
	}
	
	return GameBoard;
	
});
