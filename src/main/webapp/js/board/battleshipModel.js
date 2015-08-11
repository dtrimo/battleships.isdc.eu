define(['jquery', 'transforms'],function($, Transforms){
	
	//id is an identifier of the BattleshipModel (perhaps the id in the db)
	//originalCoords is an array of coordinates of the cells of the ship. For example
	// original coords could be [{x:1,y:2},{x:1,y:3},{x:4,y:-1}]
	var BattleshipModel = function(id,originalCoords) {
		if ($.isPlainObject(id)){			
			this.id = $.extend(true,{},id);
		} else {
			this.id = id;
		}
		this.originalCoords = originalCoords;
		this.transforms = [];
		//The array of coordinates obtained by applying all transforms to the originalCoords
		this.transformedCoords = this.originalCoords.slice(0);
		this.views = [];
	}; 
	
	BattleshipModel.prototype.getCellCount = function(){
		return this.originalCoords.length;
	}
	
	var getCoords = function(coords, fieldName){
		return $.map(coords,function(val,index){
			return val[fieldName];
		});
	}
	
	/*********************************************************************/
	
	BattleshipModel.prototype.getOriginalCoords = function() {
		return this.originalCoords.slice(0);
	};
	
	BattleshipModel.prototype.getOriginalXCoords = function() {
		return getCoords(this.originalCoords, "x");
	};

	BattleshipModel.prototype.getOriginalYCoords = function() {
		return getCoords(this.originalCoords, "y");
	};

	/*********************************************************************/
	
	BattleshipModel.prototype.getTransforms = function() {
		return this.transforms.slice(0);
	}

	BattleshipModel.prototype.getTransformedCoords = function() {
		return this.transformedCoords.slice(0);
	}

	BattleshipModel.prototype.getTransformedXCoords = function() {
		return getCoords(this.transformedCoords, "x");
	};
	
	BattleshipModel.prototype.getTransformedYCoords = function() {
		return getCoords(this.transformedCoords, "y");
	};
	
	BattleshipModel.prototype.applyTransform = function(transform) {
		this.transforms.push(transform);
		this.transformedCoords = transform.applyInverseArray(this.transformedCoords);
	};

	BattleshipModel.prototype.undoTransform = function(){
		if (this.transforms.length==0){
			return false;
		}
		this.transformedCoords = this.transforms[this.transforms.length-1]
			.applyInverseArray(this.transformedCoords);
		this.transforms.splice(this.transforms.length-1,1);
		return true;
	};
	
	BattleshipModel.prototype.undoAllTransforms = function(){
		this.transforms = [];
		this.transformedCoords = this.originalCoords.slice(0);
	}

	/*********************************************************************/
	
	//Methods for registering and unregistering views are useful
	//for implementing requirements such as having a single view per model 
	//or for implementing a standard observer pattern
	BattleshipModel.prototype.registerView = function(view){
		this.views.push(view);
	}
	
	BattleshipModel.prototype.unregisterView = function(view){
		var index = this.views.indexOf(view);
		if (index >=0 ){
			this.views.splice(index,1);
			return true;
		} else {
			return false;
		}
	}
	
	BattleshipModel.prototype.hasView = function(view){
		return this.views.indexOf(view) >= 0;
	}
	
	/*********************************************************************/
	
	BattleshipModel.prototype.clone = function(){
		var clone = new BattleshipModel(this.id,this.originalCoords.slice(0));
		clone.transforms = this.transforms.slice(0);
		clone.transformedCoords = this.transformedCoords.slice(0);
		return clone;
	}
	
	return BattleshipModel;
	
});
