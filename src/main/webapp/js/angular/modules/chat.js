var module = angular.module("chat",["uiUtilities"]);

module.directive("chatWindow", function(){
	
	return {
		templateUrl : window.baseUrl+"/html/chatWindow.html"
	}
	
});

module.directive("chatManager", function(){

	return {
		templateUrl : window.baseUrl+"/html/chatManager.html"
	}
	
});
