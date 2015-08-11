define(['jquery','selectionBoard'],function($,SelectionBoard){
	
	var SelectionBoardController = function(selectionBoard){
		this.selectionBoard = selectionBoard;
	}
	
	SelectionBoardController.prototype.init = function(){
		bindShipClickHandler(this.selectionBoard);
		bindShipAddedHandler(this.selectionBoard);
		bindSelectedShipChangedHandler(this.selectionBoard);
	}
	
	SelectionBoardController.prototype.destroy = function(){
		this.selectionBoard.board.$container.off(".selectionBoard");
		$(document).off("shipRemoved.gameBoard");
		$(document).off("shipChanged.transformationBoard");
	}
	
	var bindShipClickHandler = function(selectionBoard){
		selectionBoard.board.$container.off("click.selectionBoard","."+selectionBoard.board.shipClass)
		.on("click.selectionBoard","."+selectionBoard.board.shipClass,function(){
			var $ship = $(this);
			var shipModel = $ship.data("shipView").getModel();
			selectionBoard.hideShip(shipModel);			
			$(document).trigger("shipRemoved.selectionBoard",shipModel);
		});		
	}
	
	var bindShipAddedHandler = function(selectionBoard){
		$(document).off("shipRemoved.gameBoard").on("shipRemoved.gameBoard",function(event,shipView){
			var shipModel = shipView.getModel();
			shipModel.undoAllTransforms();
			selectionBoard.showShip(shipModel);
		});
	}
	
	var bindSelectedShipChangedHandler = function(selectionBoard){
		$(document).off("shipChanged.transformationBoard").on("shipChanged.transformationBoard",function(event,shipView){
			var shipModel = shipView.getModel();
			shipModel.undoAllTransforms();
			selectionBoard.showShip(shipModel);
		})
	}
	
	return SelectionBoardController;
});