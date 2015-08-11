define([],function(){
	
	var Transformation = function(){};
	
	Transformation.prototype.applyDirectArray = function(coordsArray){
		var self = this;
		return $.map(coordsArray,function(coords,index){
			return self.applyDirect(coords);
		});
	};
	
	Transformation.prototype.applyInverseArray = function(coordsArray){
		var self = this;
		return $.map(coordsArray,function(coords,index){
			return self.applyInverse(coords);
		});
	};
	
	// Rotate clockwise with 90 degrees
	var RotateClockwise = function(){
		this.name="rotateClockwise";
	};
	
	RotateClockwise.prototype = Object.create(Transformation.prototype);
	
	RotateClockwise.prototype.applyDirect = function(coords){
		return {
			x : coords.y, 
			y : -coords.x-1
		};
	};
	
	RotateClockwise.prototype.applyInverse = function(coords){
		return {
			x : -coords.y-1,
			y : coords.x
		}
	};
	
	// Rotate counterclockwise with 90 degrees
	var RotateCounterClockwise = function(){
		this.name="rotateCounterClockwise";
	};
	
	RotateCounterClockwise.prototype = Object.create(Transformation.prototype);
	
	RotateCounterClockwise.prototype.applyDirect = function(coords){
		return {
			x : -coords.y-1,
			y : coords.x
		}
	};
	
	RotateCounterClockwise.prototype.applyInverse = function(coords){
		return {
			x : coords.y, 
			y : -coords.x-1
		};
	};
	
	// Reflect across the Oy axis
	var ReflectY = function(){
		this.name = "reflectY";
	};
	
	ReflectY.prototype = Object.create(Transformation.prototype);
	
	ReflectY.prototype.applyDirect = function(coords){
		return {
			x : -coords.x-1,
			y : coords.y
		}
	};
	
	ReflectY.prototype.applyInverse = function(coords){
		return {
			x : -coords.x-1, 
			y : coords.y
		};
	};
	
	// Reflect across the Ox axis
	var ReflectX = function(){
		this.name = "reflectX";
	};
	
	ReflectX.prototype = Object.create(Transformation.prototype);
	
	ReflectX.prototype.applyDirect = function(coords){
		return {
			x : coords.x,
			y : -coords.y-1
		}
	};
	
	ReflectX.prototype.applyInverse = function(coords){
		return {
			x : coords.x, 
			y : -coords.y-1
		};
	};	
	
	return {
		rotateClockwise : new RotateClockwise(),
		rotateCounterClockwise : new RotateCounterClockwise(),
		reflectX : new ReflectX(),
		reflectY : new ReflectY()
	};
	
});