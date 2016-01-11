var module = angular.module("homepage", ["users", "onlinePresence", "chat"]);
module.controller("HomepageController", ['$scope', '$rootScope', 'userService', 'onlinePresenceService', function($scope, $rootScope, userService, onlinePresenceService){
	
	userService.getCurrentUser().then(function(response){
		$scope.user = response.data;
		$rootScope.$broadcast('userPopulated', $scope.user);
		onlinePresenceService.openWSConnection($scope.user.userId, $scope.user.name);
	});
	
}]);

module.controller("HeaderController", ['$scope', '$window', function($scope, $window){
	
	$scope.logout = function(){
		$window.alert("logout clicked");
	};
	
}]);

module.directive("loggedInHeader", function(){
	return {
		templateUrl : window.baseUrl+"/html/loggedInHeader.html"		
	}
});

module.directive("friendsList", function(){
	return {
		templateUrl : window.baseUrl+"/html/friendsList.html"	
	}
});

module.controller("FriendsController", ['$scope', '$rootScope', 'friendsService', '$window', function($scope, $rootScope, friendsService, $window){
	
	$rootScope.$on("userPopulated", function(event, user){
		$scope.friends = friendsService.getFriendsForUser(user.userId);
	});
	
	$scope.$on("online_presence_change",function(event, message){
		$scope.$apply(function(){
			if (!$scope.friends){
				return;
			}
			for (var i=0;i<$scope.friends.length;i++){
				if ($scope.friends[i].userId == message.userId){
					$scope.friends[i].online = (message.type == "CONNECTED");
				}
			}			
		});
	});
	
	 	
 	$scope.formatConversationLog = function(messages){
 		if (messages && messages.length){
 			return $.map(messages, function(message){
 				return message[0]+" : "+message[1];
 			}).join("\n");
 		} else {
 			return "";
 		}
 	};
	
	$scope.initiateConversation = function(friend){
		if (!friend.online){
			friend.conversationVisible = false;
		} else {
			friend.conversationVisible = !friend.conversationVisible;
		}
	}
	
	$scope.$on("user_message_received", function(event, message){
		var friend = null;
		for (var i=0; i<$scope.friends.length;i++){
			if ($scope.friends[i].name == message.senderName){
				friend = $scope.friends[i];
				break;
			}
		}
		if (friend!=null){
			$scope.$apply(function(){
				friend.conversationVisible = true;
				if (!friend.conversationLog){
					friend.conversationLog = [];
				}
			});								
		}
	});
	
}]);


module.controller("ChatWindowController", ["$scope", 'onlinePresenceService', function($scope, onlinePresenceService){
	
	$scope.sendMessage = function(){
		if (!$scope.message){
			return;
		}
		var stompClient = onlinePresenceService.getStompClient($scope.user.userId);
		var message = {
			senderName : $scope.user.name,
			receiverName : $scope.friend.name,
			message : $scope.message,
			timestamp : new Date()
		};
		if (!$scope.friend.conversationLog){
			$scope.friend.conversationLog = [];
		}
		$scope.friend.conversationLog.push([message.senderName, message.message]);
		$scope.message = "";
		stompClient.send("/messaging/sendMessage", {}, JSON.stringify(message));		
	}
	
	$scope.$parent.$on("user_message_received", function(event, message){
		if ($scope.friend.name == message.senderName){
			$scope.$applyAsync(function(){
				$scope.friend.conversationLog.push([message.senderName, message.message]);
			});
		}
	});
	
}]);
