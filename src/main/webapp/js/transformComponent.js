(function() {
	Transform = function() {
	}
		
	Transform.prototype.reflectX = function(position) {
		var xCoords = position.getXCoords();
		var yCoords = position.getYCoords();
		
		for (var i = 0; i < yCoords.length; i++)
			yCoords[i] = -yCoords[i] - 1;
		
		return {
			xCoords: xCoords,
			yCoords: yCoords
		}
	}
	
	Transform.prototype.reflectY = function(position) {
		var xCoords = position.getXCoords();
		var yCoords = position.getYCoords();
		
		for (var i = 0; i < xCoords.length; i++)
			xCoords[i] = -xCoords[i] - 1;
		
		return {
			xCoords: xCoords,
			yCoords: yCoords
		}
	}
	
	Transform.prototype.rotateClockwise = function(position) {
		var xCoords = position.getXCoords();
		var yCoords = position.getYCoords();
		
		for (var i = 0; i < yCoords.length; i++) {
			var aux = xCoords[i];
			xCoords[i] = yCoords[i];
			yCoords[i] = -aux - 1;
		}
		
		return {
			xCoords: xCoords,
			yCoords: yCoords
		}
	}
	
	Transform.prototype.rotateCounterClockwise = function(position) {
		var xCoords = position.getXCoords();
		var yCoords = position.getYCoords();
		
		for (var i = 0; i < yCoords.length; i++) {
			var aux = xCoords[i];
			xCoords[i] = -yCoords[i] - 1;
			yCoords[i] = aux;
		}
		
		return {
			xCoords: xCoords,
			yCoords: yCoords
		}
	}
})();
