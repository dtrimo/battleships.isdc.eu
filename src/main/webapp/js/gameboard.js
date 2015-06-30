var generateBoard = function(){
	var mGameBoard;
	var nGameBoard;
	var $container;

	var init = function(lines,columns,tableContainer){
		nGameBoard = lines;
		mGameBoard = columns;
		$container = $(tableContainer); 
	}

	var drawBoard = function(){
		for(var i = 0; i < nGameBoard ; i++){
			var $divRow = $('<div><div>');
			$divRow.addClass('div-row');
			for(var j = 0; j < mGameBoard; j++){
				var $div = $('<div></div>');
				$divRow.append($div);
			}
			$container.append($divRow);
		}

	}
 	
 	//board has n lines and m columns
	var drawShipsBoard = function(m, n){
		var $shipsContainer = $('.ships-container');

		for(var i = 0; i < n; i++){
			var $divRow = $('<div><div>');
			$divRow.addClass('div-row');
			for(var j = 0; j < m; j++){
				var $div = $('<div></div>');
				$divRow.append($div);
			}
			$shipsContainer.append($divRow);
		}
		$shipsContainer.data( "size", { lines: n, columns: m });
	}

	//draw ships in shipsContainer using arrays with coordinates x,y; ship starts at offset
	//board has n lines and m columns
	//offsetUp - distance from top to ship(in cells -> .ship-cell)
	//offset - distance from left to ship(in cells -> .ship-cell)
	var drawShip = function(x, y, offsetX, offsetY, shipsContainer){

		var maxX = Math.max.apply(null, x);
		var minX = Math.min.apply(null, x); 
		var maxY = Math.max.apply(null, y);
		var minY = Math.min.apply(null, y); 
		
		var shipCellWidth = maxX-minX+1;
		var shipCellHeight = maxY-minY+1;
		
		var $divShip = $('<div></div>');
		$divShip.addClass('div-ship');
		
		if (shipsContainer.is('.transformation-container')){
			$divShip.addClass('transformShip');
		} else {
			$divShip.addClass('modelShip');
		}
		
		
		var $div = $($(shipsContainer).find('.div-row')[0]);
		var $cell = $($div.find(' > div')[0]);
		var cellHeight = $cell.outerHeight();
		var cellWidth = $cell.outerWidth();

		$divShip.outerWidth(cellWidth * shipCellWidth);
		$divShip.outerHeight(cellHeight * shipCellHeight);
		if ($(shipsContainer).is(".transformation-container")){
			$(shipsContainer).find(".transformation-grid").append($divShip);		
		} else {
			$(shipsContainer).append($divShip);			
		}


		for(var i = 0;i<shipCellHeight;i++){
			var $divRow = $('<div class="ship-row"></div>');
			for(var j = 0; j < shipCellWidth; j++){
				var $div = $('<div class="ship-cell"></div>');
				$div.outerWidth(cellWidth);
				$div.outerHeight(cellHeight);
				$divRow.append($div);
			}
			$divShip.append($divRow);
		}

		var left = (offsetX+minX)*cellWidth;
		$divShip.css('left',left + 'px');

		for(var i = 0; i < y.length; i++){
			var $div = $($divShip.find('.ship-row')[shipCellHeight-y[i]+minY-1]);
			var $cell = $($div.find(' > div')[x[i]-minX]);
			$cell.css('background-color','black');
		}

		var bottom = (offsetY+minY)*cellHeight;
		$divShip.css('bottom', bottom + 'px');

		$divShip.data("position",BattleshipPosition(x,y));
		
		return shipCellWidth+1;
	}

	//draws transfomations container whit m x n cells
	var drawTransformationContainer = function(m, n){
		var $container = $('.transformation-container');
		var $grid = $container.find(".transformation-grid");
		
		for(var i = 0; i < n ; i++){
			var $divRow = $('<div><div>');
			$divRow.addClass('div-row');
			for(var j = 0; j < m; j++){
				var $div = $('<div></div>');
				$divRow.append($div);
			}
			
			$grid.append($divRow);
		}
		
		$container.data( "size", { lines: n, columns: m});
		
	} 


	return{
		init : init,
		drawBoard : drawBoard,
		drawShipsBoard : drawShipsBoard,
		drawShip: drawShip,
		drawTransformationContainer : drawTransformationContainer
	}
};

var Transforms = {		
		rotateCounterClockwise : function(position){
			return {
				x: -position.y,
				y: position.x
			}
		},		
		rotateClockwise : function(position){
			return {
				x: position.y,
				y: -position.x
			}
		},		
		reflectX : function(position){
			return {
				x : position.x,
				y : -position.y
			}
		},		
		reflectY : function(position){
			return {
				x: -position.x,
				y: position.y
			}	
		},
		translate : function(position,x,y){
			return {
				x : position.x+x,
				y : position.y+y
			}
		}
}


var BattleshipPosition = function(xCoords, yCoords){
	
	var transforms = [];
	var cartesianCoords = [];
	
	var initialCoords = [];
	for (var i=0;i<xCoords.length;i++){
		initialCoords.push({
			x:xCoords[i],
			y:yCoords[i]
		});
	}
	
	var reset = function(){
		transforms = [];
		cartesianCoords = [];
		for (var i=0;i<initialCoords.length;i++){
			cartesianCoords.push({
				x:initialCoords[i].x+0.5,
				y:initialCoords[i].y+0.5
			});
		}
	};
	
	//Compute the initial coords
	reset();
	
	var reflectX = function(){
		transforms.push(Transforms.reflectX);
		for (var i=0;i<cartesianCoords.length;i++){
			cartesianCoords[i] = Transforms.reflectX(cartesianCoords[i]);			
		}
	};
	
	var reflectY = function(){
		transforms.push(Transforms.reflectY);
		for (var i=0;i<cartesianCoords.length;i++){
			cartesianCoords[i] = Transforms.reflectY(cartesianCoords[i]);			
		}
	};
	
	var rotateClockwise = function(){
		transforms.push(Transforms.rotateClockwise);
		for (var i=0;i<cartesianCoords.length;i++){
			cartesianCoords[i] = Transforms.rotateClockwise(cartesianCoords[i]);			
		}
	};
	
	var rotateCounterClockwise = function(){
		transforms.push(Transforms.rotateCounterClockwise);
		for (var i=0;i<cartesianCoords.length;i++){
			cartesianCoords[i] = Transforms.rotateCounterClockwise(cartesianCoords[i]);			
		}
	};
	
	var getCartesianCoords = function(){
		var result = [];
		for (var i=0;i<cartesianCoords.length;i++){
			result.push(cartesianCoords[i]);
		}
		return result;
	};
	
	var getCoords = function(){
		var result = [];
		for (var i=0;i<cartesianCoords.length;i++){
			result.push(Transforms.translate(cartesianCoords[i],-0.5,-0.5));
		}
		return result;
	};
	
	var getXCoords = function(){
		var coords = getCoords();
		var result = [];
		for (var i=0;i<coords.length;i++){
			result.push(coords[i].x);
		}
		return result;
	};
	
	var getYCoords = function(){
		var coords = getCoords();
		var result = [];
		for (var i=0;i<coords.length;i++){
			result.push(coords[i].y);
		}
		return result;
	};
	
	
	return {
		reflectX : reflectX,
		reflectY : reflectY,
		rotateClockwise : rotateClockwise,
		rotateCounterClockwise : rotateCounterClockwise,
		getCartesianCoords : getCartesianCoords,
		getCoords : getCoords,
		getXCoords : getXCoords,
		getYCoords : getYCoords,
//		undo : undo,
//		redo : redo,
		reset : reset
	};
}


 $(function(){
	 
	 /*
	 
	 $.ajax({
	 	url: "http:\\localhost:8080\battleships\game",
	 	method: "GET",
	 	success: function(data){gameEditing(data)},
	 	error
	 });*/
	 
	 gameEditing();	//ONLY FOR EXAMPLE PURPOSE...
 })
 
var gameEditing = function(data){
	 
	var $selectedShip = null;
	var $draggingShip = null;
 	var board = generateBoard();
 	var $container =  $($('.board-container')[0])
	var $shipsContainer = $('.ships-container');
 	var $transformationContainer = $('.transformation-container');

 	
 	
 	
 	
 	//Send to server Game type information from the users
 	//Get From Server response
 	
 	board.init(9, 9, $container);
 	board.drawBoard();
 	board.drawShipsBoard(23, 4);
	
 	
 	/*	Draw ships in ships-container - June 25. 2015. added idIndex, so 
 	 * 	redraw function could be easier. Supposed BUGS could be available   */
	var offset = 0;
	
 	var x = [0,1,2,1];
	var y = [0,0,0,1];
 	offset += board.drawShip(x, y, offset, 0, $shipsContainer);

 	var x1 = [0,1,2,1,1];
 	var y1 = [1,0,1,1,2];
 	offset += board.drawShip(x1, y1, offset, 0, $shipsContainer);
 
 	var x2 = [0,0,0,1];
 	var y2 = [0,1,2,0];
 	offset += board.drawShip(x2, y2, offset, 0, $shipsContainer);

 	var x3 = [0,1,2];
 	var y3 = [0,0,0];
 	offset += board.drawShip(x3, y3, offset, 0, $shipsContainer);

 	var x4 = [0,1,2,3,4];
 	var y4 = [0,0,0,0,0];
 	offset += board.drawShip(x4, y4, offset, 0, $shipsContainer);

 	board.drawTransformationContainer(14, 14);
 	
	
 	//Dragging
 	
 	$('.transformation-container, .board-container').on('mouseover',function(){

 		$('.transformShip, .gameBoardShip').draggable({
 	 		cursor: 'move',
 	 		revert: 'invalid',
 	 		start: function(event, ui) {
 	 			$draggingShip = $(this);
 	 		}
 		})
 	});
 	
 	//	NO RIGHTCLICK MENU :D YEEEY
 	
 	$('.board-container').off("contextmenu.rclick",".gameBoardShip").on("contextmenu.rclick",".gameBoardShip",function(e){ 
	 		e.preventDefault();
	 		e.stopPropagation();
	 		return false;
 	});
 	
 	//	RightClick function: Needs to be edited, info below.

	$('.board-container').off("mousedown.rclick",".gameBoardShip").on("mousedown.rclick",".gameBoardShip",function(e){ 
		    if( e.button == 2 ) { 
		    	var $this = $(this);
		    	setTimeout(function (){
		    		$this.remove();
		    	},200);
		 		e.preventDefault();
		 		e.stopPropagation();
		 		return false;
		    } 
	});  

 	
 	
 	/////////////////////////////////06.23.2015 - Handlers/////////////////////////////////////////////////////////
 	$('.modelShip').click(function(){ 		
 		$('div.transformShip').remove();
 		var $ship = $(this);	
 		var xCoords = $ship.data("position").getXCoords();
 		var yCoords = $ship.data("position").getYCoords();
 	 	board.drawShip(xCoords, yCoords, 6, 6, $transformationContainer);
 		$selectedShip = $(this);
	});
 	
 	// Needs to be edited: store commands to vector(s).
 	var applyTransformation = function(transformationName){
 		var $ship = $('div.transformShip');
 		var position = $ship.data("position");
 		position[transformationName]();
 		var xCoords = $ship.data("position").getXCoords();
 		var yCoords = $ship.data("position").getYCoords();
 		$ship.remove();
 	 	board.drawShip(xCoords, yCoords, 6, 6, $transformationContainer);	
 	}
 	
 	
 			//ROTATIONS
 	$('button#left').click(function(){ 		
 		applyTransformation("rotateCounterClockwise");
 	});
 	
 	$('button#right').click(function(){
 		applyTransformation("rotateClockwise");
 	});
 	
 	$('button#reflectY').click(function(){
 		applyTransformation("reflectY");
 	});
 	
 	$('button#reflectX').click(function(){
 		applyTransformation("reflectX");
 	})
 	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
 	$('.board-container').droppable({
 		revert: false,
 		tolerance:'fit',
 		over: function(event, ui){
 			
 			//DROP SHADOW HERE
 			
 		},
		deactivate: function(event, ui){
			
			var d1 = $(".board-container");
			var d2 = $(".transformation-grid");
			var dropPosition = $(ui.draggable).position();
			var topPosition;
			var leftPosition;
			
			var $ship = $draggingShip;
			if ($ship.hasClass("gameBoardShip")){
				
				topPosition = dropPosition.top;
				leftPosition = dropPosition.left;
					
			} else {
				
				//The offset of the game board (top-left corner)
				var y = d1.offset();
				//The offset of the transformation board (top-left corner)
				var x = d2.offset();
				var rel = { left: y.left-x.left, top: y.top-x.top };
				
				var topPosition = dropPosition.top - rel.top;
				var leftPosition = dropPosition.left - rel.left;
				
			}
			
    		if( leftPosition + $ship.width() < d1.width() &&
    			topPosition + $ship.height() < d1.height() &&
    			leftPosition > 0 &&
    			topPosition > 0)
    		{
    			if ($ship.hasClass("transformShip")){
    				$ship.addClass("gameBoardShip");
    				$ship.detach();
    				$ship.removeClass("transformShip");
    				$ship.appendTo(d1);
    			}			
    			if(topPosition % 40 != 0){
    				topPosition = Math.round(topPosition / 40) * 40;
    			}


    			if(leftPosition % 40 != 0){
    				leftPosition = Math.round(leftPosition / 40) * 40;
    			}
    			

    			$ship.css('top', topPosition + 'px');
    			$ship.css('left', leftPosition + 'px');
    	
    			if ($selectedShip != null && $selectedShip.hasClass("modelShip")){
    				$selectedShip.remove();
    			}	
			}
		}
 	})
}
 
	// Stuff to do:
 /*
  * 1. JQuery Ajax requests.
  * 2. RightClick : Put back the Ship to its initial place in the model board. 
  * 3. Verify if two shapes collide
  * 4. DROP SHADOW
  * 5. Information Vector : position, rotation, reflection.
  */