var BattleshipPosition = function(xCoords, yCoords) {
	this.transforms = [];
	this.coords = [];

	for (var i = 0; i < xCoords.length; i++) {
		this.coords.push({
			x : xCoords[i],
			y : yCoords[i]
		});
	}
}

BattleshipPosition.prototype.pushTransform = function(transform) {
	this.transforms.push(transform)
}

BattleshipPosition.prototype.attachTransforms = function(transforms) {
	this.transforms = transforms;
}

BattleshipPosition.prototype.getTransforms = function() {
	return this.transforms;
}

BattleshipPosition.prototype.getCoords = function() {
	return this.coords;
};

BattleshipPosition.prototype.getXCoords = function() {
	var coords = this.getCoords();
	var result = [];
	for (var i = 0; i < coords.length; i++) {
		result.push(coords[i].x);
	}
	return result;
};

BattleshipPosition.prototype.getYCoords = function() {
	var coords = this.getCoords();
	var result = [];
	for (var i = 0; i < coords.length; i++) {
		result.push(coords[i].y);
	}
	return result;
};
