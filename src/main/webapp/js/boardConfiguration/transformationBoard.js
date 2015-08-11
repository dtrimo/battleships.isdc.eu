define(['jquery','board','jqueryUI'],function($, Board){
	TransformationBoard = function(m, n, $container, shipClass) {
		this.board = new Board(m, n, $container, shipClass);
		this.battleshipModel = null;
		this.battleshipView = null;
	}

	TransformationBoard.prototype.drawBoard = function() {
		this.board.drawBoard();
	}

	TransformationBoard.prototype.setShipModel = function(battleshipModel) {
		var previousShipView = this.battleshipView;
		if (this.battleshipView!=null){
			this.battleshipView.destroy();
		}
		this.battleshipModel = battleshipModel;
		this.battleshipView =  this.board.drawShip(this.battleshipModel, this.board.width / 2, this.board.height / 2);
		return previousShipView;
	}
	
	TransformationBoard.prototype.getShipModel = function() {
		return this.battleshipModel;
	}

	TransformationBoard.prototype.getShipView = function() {
		return this.battleshipView;
	}

	TransformationBoard.prototype.removeShip = function() {
		if (this.battleshipView != null) {
			var shipView = this.battleshipView;
			this.battleshipModel = null;
			this.battleshipView = null;
			this.board.removeShips();
			return shipView;
		}
		return null;
	}
	
	TransformationBoard.prototype.transform = function(transformation) {
		if (this.battleshipModel != null) {			
			this.battleshipModel.applyTransform(transformation);
			this.battleshipView.destroy();
			this.battleshipView =  this.board.drawShip(this.battleshipModel, this.board.width / 2, this.board.height / 2);
		}
	}
	
	return TransformationBoard;
});
