(function($){
	GameBoard = function(m, n, $container, shipClass, transformBoard) {
		this.m = m;
		this.n = n;
		this.$container = $container;
		this.shipClass = shipClass;
		this.transformBoard = transformBoard;
		this.board = new Board(m, n, $container, shipClass);
		bindDroppableBoardHandler(this);
	}
	
	GameBoard.prototype.drawBoard = function() {
		this.board.drawBoard();
	}
	
	var bindDroppableBoardHandler = function(gameBoard) {
	 	gameBoard.$container.droppable({
	 		revert: false,
	 		tolerance:'fit',
	 		over: function(event, ui){
	 			
	 			//DROP SHADOW HERE
	 			
	 		},
			deactivate: function(event, ui){
				
				var gameContainer = gameBoard.$container;
				
				//TODO: this is not okay, because i'm selecting the transformBoard
				//		and not the transformation-grid, which i removed from the gameboard.jsp 
				//		because I found it useless. Hardcoding the value ".transformation-grid"
				//		here is not adviced(bad practice) and could really give headaches.
				//		this still works, but it's error exposed, the calculation should behave
				//		different.
				//		initial value : $(".transformation-grid");
				var transformContainer = gameBoard.transformBoard.$container 
				
				var $ship = $(ui.draggable);
				var dropPosition = $ship.position();
				
				var topPosition;
				var leftPosition;
				
				if ($ship.hasClass(gameBoard.shipClass)){				
					topPosition = dropPosition.top;
					leftPosition = dropPosition.left;					
				} else {				
					//The offset of the game board (top-left corner)
					var offsetGameBoard = gameContainer.offset();
					//The offset of the transformation board (top-left corner)
					var offsetTransformBoard = transformContainer.offset();
					var rel = { left: offsetGameBoard.left - offsetTransformBoard.left, 
							top: offsetGameBoard.top - offsetTransformBoard.top };
					
					topPosition = dropPosition.top - rel.top;
					leftPosition = dropPosition.left - rel.left;
				}
				
	    		if( leftPosition + $ship.width() < gameContainer.width() &&
	    			topPosition + $ship.height() < gameContainer.height() &&
	    			leftPosition > 0 &&
	    			topPosition > 0)
	    		{
	    			if ($ship.hasClass(gameBoard.transformBoard.shipClass)){
	    				$ship.addClass(gameBoard.shipClass);
	    				$ship.detach();
	    				$ship.removeClass(gameBoard.transformBoard.shipClass);
	    				$ship.appendTo(gameContainer);
	    			}			
	    			
	    			if(topPosition % 40 != 0){
	    				topPosition = Math.round(topPosition / 40) * 40;
	    			}


	    			if(leftPosition % 40 != 0){
	    				leftPosition = Math.round(leftPosition / 40) * 40;
	    			}
	    			
	    			$ship.css('top', topPosition + 'px');
	    			$ship.css('left', leftPosition + 'px');
	    	
	    			//TODO: See if this is really needed here..
//	    			if ($selectedShip != null && $selectedShip.hasClass("modelShip")){
//	    				$selectedShip.remove();
//	    			}	
				}
			}
	 	});
	}
})(jQuery);