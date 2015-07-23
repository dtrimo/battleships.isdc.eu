(function() {
	Transform = function() {
	}
		
	Transform.prototype.reflectX = function(position) {
		var xCoords = position.getXCoords();
		var yCoords = position.getYCoords();
		
		for (var i = 0; i < yCoords.length; i++)
			yCoords[i] = -yCoords[i] - 1;
		
		return new BattleshipPosition(xCoords, yCoords);
	}
	
	Transform.prototype.reflectY = function(position) {
		var xCoords = position.getXCoords();
		var yCoords = position.getYCoords();
		
		for (var i = 0; i < xCoords.length; i++)
			xCoords[i] = -xCoords[i] - 1;
		
		return new BattleshipPosition(xCoords, yCoords);
	}
	
	Transform.prototype.rotateClockwise = function(position) {
		var xCoords = position.getXCoords();
		var yCoords = position.getYCoords();
		
		for (var i = 0; i < yCoords.length; i++) {
			var aux = xCoords[i];
			xCoords[i] = yCoords[i];
			yCoords[i] = -aux - 1;
		}
		
		return new BattleshipPosition(xCoords, yCoords);
	}
	
	Transform.prototype.rotateCounterClockwise = function(position) {
		var xCoords = position.getXCoords();
		var yCoords = position.getYCoords();
		
		for (var i = 0; i < yCoords.length; i++) {
			var aux = xCoords[i];
			xCoords[i] = -yCoords[i] - 1;
			yCoords[i] = aux;
		}
		
		return new BattleshipPosition(xCoords, yCoords);
	}
	
	Transform.prototype.resetInitialPosition = function(transforms, position) {
		var length = transforms.length;
		for (var i = length - 1; i >= 0; i--) {
			if (transforms[i] == Transform.reflectX)
				position = Transform.reflectX(position);
			else if (transforms[i] == Transform.reflectY)
				position = Transform.reflectY(position);
			else if (transforms[i] == Transform.rotateClockwise)
				position = Transform.rotateCounterClockwise(position);
			else if (transforms[i] == Transform.rotateCounterClockwise)
				position = Transform.rotateClockwise(position);			
		}
		return position;
	}
	
	Transform.prototype.getTransformName = function(func) {
		  if (func == Transform.reflectX)
		   return "reflectX";
		  else if (func == Transform.reflectY)
		   return "reflectY";
		  else if (func == Transform.rotateClockwise)
		   return "rotateClockwise";
		  else if (func == Transform.rotateCounterClockwise)
		   return "rotateCounterClockwise";
		  return null;
		 }
})();
