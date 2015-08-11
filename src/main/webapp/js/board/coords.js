define([],function(){
	
	var Coords = function(x,y){
		this.x=x;
		this.y=y;
	}
	
	Coords.prototype.clone = function(){
		return new Coords(this.x,this.y);
	}
	
	Coords.prototype.applyTranslation = function(x,y){
		this.x += x;
		this.y += y;
		return this;
	}
	
	return Coords;
	
});
