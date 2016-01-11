var module = angular.module("users",['ngResource']);

module.factory('userService', ['$http', function($http){
	
	var getCurrentUser = function(){
		return $http({
			method : "GET",
			url : window.baseUrl+"/currentuser"
		});
	};
	
	return {
		getCurrentUser : getCurrentUser
	};
	
}]);

module.factory('friendsService', ['$resource', function($resource){
	
	var getFriendsForUser = function(userId){
		var friends = $resource(window.baseUrl+"/user/:userId/friends", 
				{userId: "@id"});
		return friends.query({userId : userId});
	}
	
	return {
		getFriendsForUser : getFriendsForUser
	};
}]);
