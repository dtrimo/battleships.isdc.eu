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
})();
