define(['jquery','transformationBoard', 'transforms'],function($,TransformationBoard, Transforms){
	
	var TransformationBoardController = function(transformationBoard, $controlsContainer){
		this.transformationBoard = transformationBoard;
		this.$controlsContainer = $($controlsContainer);
	};
	
	TransformationBoardController.prototype.init = function(){
		bindReflectXBtnHandler(this.transformationBoard, this.$controlsContainer);
		bindReflectYBtnHandler(this.transformationBoard, this.$controlsContainer);
		bindRotateClockwiseBtn(this.transformationBoard, this.$controlsContainer);
		bindRotateCounterClockwiseBtn(this.transformationBoard, this.$controlsContainer);
		bindShipSelectedHandler(this.transformationBoard);
		bindShipAddedToGameboardHandler(this.transformationBoard);
	};
	
	TransformationBoardController.prototype.destroy = function(){
		this.$controlsContainer.find(".transform").off(".transformation");
		$(document).off("shipRemoved.selectionBoard");
		$(document).off("shipAdded.gameBoard");
	};
	
	var bindReflectXBtnHandler = function(transformationBoard, $controlsContainer) {
	 	$controlsContainer.find('button.reflectX').off("click.transformation").on("click.transformation",function(){
	 		transformationBoard.transform(Transforms.reflectX);
	 		bindDraggableShipHandler(transformationBoard);
	 	})
	};
	
	var bindReflectYBtnHandler = function(transformationBoard, $controlsContainer) {
		$controlsContainer.find('button.reflectY').off("click.transformation").on("click.transformation",function(){
	 		transformationBoard.transform(Transforms.reflectY);
	 		bindDraggableShipHandler(transformationBoard);
	 	})
	};
	
	var bindRotateClockwiseBtn = function(transformationBoard, $controlsContainer) {
		$controlsContainer.find('button.rotateClockwise').off("click.transformation").on("click.transformation",function(){
	 		transformationBoard.transform(Transforms.rotateClockwise);
	 		bindDraggableShipHandler(transformationBoard);
	 	})
	};
	
	var bindRotateCounterClockwiseBtn = function(transformationBoard, $controlsContainer) {
		$controlsContainer.find('button.rotateCounterClockwise').off("click.transformation").on("click.transformation",function(){
	 		transformationBoard.transform(Transforms.rotateCounterClockwise);
	 		bindDraggableShipHandler(transformationBoard);
	 	})
	};

	var bindShipSelectedHandler = function(transformationBoard){
		$(document).off("shipRemoved.selectionBoard").on("shipRemoved.selectionBoard",function(event,shipModel){
			var previousShipView = transformationBoard.setShipModel(shipModel);
			if (previousShipView!=null){				
				$(document).trigger("shipChanged.transformationBoard",previousShipView);
			}
			bindDraggableShipHandler(transformationBoard);
		});
	};
	
	var bindShipAddedToGameboardHandler = function(transformationBoard){
		$(document).off("shipAdded.gameBoard").on("shipAdded.gameBoard",function(event,shipView){
			var removedShip = transformationBoard.removeShip();
			if (removedShip!=null){
				$(document).trigger("shipRemoved.transformationBoard",removedShip);				
			}
		});
	};
	
	var bindDraggableShipHandler = function(transformationBoard) {
		if (transformationBoard.battleshipView!=null){
			transformationBoard.battleshipView.$divShip.draggable({
				handle : '.ship-cell',
				cursor : 'move',
				revert : 'invalid'
			});			
		}
	};
	
	return TransformationBoardController;
	
});