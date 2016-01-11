define(['jquery'],function($){
	
	var stompClients = {};
	
	var connect = function(userId,gameId, startConfigId) {
		if (!stompClients.hasOwnProperty(userId)){
			var socket = new SockJS('/battleships/gameLifeCycle');
			var stompClient = Stomp.over(socket);
			stompClient.connect({userId : userId}, function(frame) {
				console.log('Connected: ' + frame);
				stompClients[userId].connected = true;
				drainInvokeQueue(stompClients[userId]);
			});			
			var entry = {
					userId : userId,
					gameId : gameId,
					startConfigId : startConfigId,
					client : stompClient,
					connected : false,
					invokeQueue : []
			};
			stompClients[userId] = entry;
			return entry;
		} else {
			return stompClients[userId];
		}
	};
	
	var drainInvokeQueue = function(stompClient){
		if (stompClient.connected){
			for (var i=0;i<stompClient.invokeQueue.length;i++){
				stompClient.invokeQueue[i]();
			}			
			stompClient.invokeQueue = [];
		}
	};
	
	var disconnect = function(userId){
		var client = stompClients[userId].client;
		client.disconnect();
		delete stompClients[userId];
	};
	
	var subscribe = function(stompClient, channel, callback){
		//default channel should be "/game/"+gameId+"/"+userId
		stompClient.invokeQueue.push(function(){
			stompClient.client.subscribe(channel, function(result){
				var message = JSON.parse(result.body);
				callback(message);
			});			
		});
		drainInvokeQueue(stompClient);
	};
	
	var getStompClient = function(userId){
		return stompClients[userId];
	};
	
	return {
		openWSConnection : connect,
		closeWSConnection : disconnect,
		getStompClient : getStompClient,
		subscribe : subscribe
	};
	
});