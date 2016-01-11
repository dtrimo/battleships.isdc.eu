var module = angular.module("di-examples",[]);

module.provider("dep1",function(){
	var innerValue = 1;
	
	this.getInnerValue = function(value){
		return innerValue;
	};
	
	this.$get=function(){
		return innerValue + 1;
	};
});

module.provider("dep2",
	["dep1Provider", function(dep1Provider){
		this.$get=function(){
			return dep1Provider.getInnerValue();
		}
	}]
);