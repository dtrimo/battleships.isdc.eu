var module = angular.module("onlinePresence", []);

module.factory("onlinePresenceService", ['$rootScope', function($rootScope){
	
	var stompClients = {};
	
	var connect = function(userId, userName) {
		if (!stompClients[userId]){
			var socket = new SockJS('/battleships/onlinePresence');
			var stompClient = Stomp.over(socket);
			stompClient.connect({userId : userId}, function(frame) {
				console.log('Connected: ' + frame);
				subscribe(stompClient, userName)
			});			
			stompClients[userId] = stompClient;
		}
	};
	
	var disconnect = function(userId){
		//TODO: actually close connection
		delete stompClients[userId];
	}
	
	var subscribe = function(stompClient, userName){
		stompClient.subscribe('/topic/onlinePresence', function(result){
			var message = JSON.parse(result.body);
			$rootScope.$broadcast("online_presence_change", message);
		});
		
		stompClient.subscribe("/user/"+userName+"/abc", function(result){
			var message = JSON.parse(result.body);
			$rootScope.$broadcast("user_message_received", message);
		});
	}
	
	var getStompClient = function(userId){
		return stompClients[userId];
	}
	
	return {
		openWSConnection : connect,
		closeWSConnection : disconnect,
		getStompClient : getStompClient
	}
	
}]);

//stompClient.send("/messaging/add", {}, JSON.stringify({ 'a': 11, 'b': 23 }));