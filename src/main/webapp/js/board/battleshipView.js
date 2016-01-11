define(['jquery','battleshipModel', 'coords'],function($,BattleshipModel,Coords){
	
	//BattleshipViews render the battleship with the transformations as they were at the
	//time of construction (the model is cloned and any transformations applied to it later
	//do not affect the view)
	var BattleshipView = function(battleshipModel){
		this.battleshipModel = battleshipModel;
		this.battleshipModel.registerView(this);
		this.battleshipModelSnapshot = this.battleshipModel.clone();
		this.createViewContainer();
	}
	
	BattleshipView.prototype.processModel = function(){
		var xCoords = this.battleshipModelSnapshot.getTransformedXCoords();
		var maxX = Math.max.apply(null, xCoords);
		var minX = Math.min.apply(null, xCoords);
		var shipCellWidth = maxX - minX + 1;
		this.minX = minX;
		this.shipCellWidth = shipCellWidth;

		var yCoords = this.battleshipModelSnapshot.getTransformedYCoords();		
		var maxY = Math.max.apply(null, yCoords);
		var minY = Math.min.apply(null, yCoords);
		var shipCellHeight = maxY - minY + 1;
		this.minY = minY;
		this.shipCellHeight = shipCellHeight;
	}
	
	BattleshipView.prototype.createViewContainer = function(){
		this.processModel();
		this.$divShip = $('<div class="ship"></div>');
			
		//In the database, ships positions are given so that the ship is
		//in the first quadrant and touches the X and Y axes. The computed coordinates
		//here may not satisfy this condition. We store the offset which needs to be
		//applied to the ship to move it to the first quadrant
		this.$divShip.data("first-quadrant-offset",{
			x : this.minX,
			y : this.minY
		});
		this.$divShip.data("shipView",this);
		
		var computedCoords = this.battleshipModelSnapshot.getTransformedCoords();
		for (var i = 0; i < computedCoords.length; i++){
			var $cell = $('<div class="ship-cell"></div>');
			var coords = new Coords(computedCoords[i].x,computedCoords[i].y);
			$cell.data("absolute-position",coords.clone());
			$cell.data("ship-relative-position", coords.clone().applyTranslation(-this.minX,-this.minY));
			$cell.data("shipView",this);
			
			if (containsCoords(computedCoords,{x:coords.x+1,y:coords.y})){
				$cell.addClass("neighbor-right");
			}
			if (containsCoords(computedCoords,{x:coords.x-1,y:coords.y})){
				$cell.addClass("neighbor-left");
			}
			if (containsCoords(computedCoords,{x:coords.x,y:coords.y-1})){
				$cell.addClass("neighbor-bottom");
			}
			if (containsCoords(computedCoords,{x:coords.x,y:coords.y+1})){
				$cell.addClass("neighbor-top");
			}
			
			this.$divShip.append($cell);
		}
	}
	
	var containsCoords = function(coordsArray,coords){
		for (var i = 0; i < coordsArray.length; i++){
			if (coordsArray[i].x==coords.x && coordsArray[i].y == coords.y){
				return true;
			}
		}
		return false;
	}
	
	BattleshipView.prototype.render = function(cellWidth, cellHeight, normalized){
		this.$divShip.data("cell-size",{
			width : cellWidth,
			height : cellHeight
		});
		this.$divShip.outerWidth(cellWidth * this.shipCellWidth);
		this.$divShip.outerHeight(cellHeight * this.shipCellHeight);
		this.$divShip.children("div.ship-cell").each(function(){
			var $cell = $(this);
			$cell.outerWidth(cellWidth);
			$cell.outerHeight(cellHeight);
			var shipRelativeCoords = $cell.data("ship-relative-position");
			$cell.css({
				bottom : shipRelativeCoords.y * cellHeight,
				left : shipRelativeCoords.x * cellWidth
			});
		});
		this.normalized = normalized;
		return this.$divShip;
	}
	
	BattleshipView.prototype.destroy = function(){
		this.$divShip.remove();
		this.battleshipModel.unregisterView(this);
	}
	
	BattleshipView.prototype.getModel = function(){
		return this.battleshipModel;
	}
	
	BattleshipView.prototype.getModelSnapshot = function(){
		return this.battleshipModelSnapshot;
	}
	
	return BattleshipView;
	
});