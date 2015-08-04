(function($){
	
	$.cookie.json = true;
	
	AlertManager = function(container, id){
		this.id = id || "global";
		this.cookieName = "alertMessages-"+this.id;
		var $alertsContainer = $(container);
		if ($alertsContainer.length === 0){
			$alertsContainer = $("<div></div>");
			$alertsContainer.appendTo("body");
		};
		if (!$alertsContainer.hasClass("alertsContainer")){
			$alertsContainer.addClass("alertsContainer");			
		}
		$alertsContainer.attr("id",id);
		this.$alertsContainer= $alertsContainer;
	};
	
	AlertManager.prototype.displayAlert = function(message, type, delay, fadeInTime, displayTime, fadeOutTime){
		type = type || "success";
		fadeInTime = fadeInTime || 0;
		displayTime = displayTime || 5000;
		fadeOutTime = fadeOutTime || 600;
		if (delay==undefined){
			delay = 0;
		}
		
		var $alertContainer = $("<div class='alert "+type+"'></div>");
		var $message = $("<span></span>");
		$message.text(message);
		$message.appendTo($alertContainer);
		var $alertsContainer = this.$alertsContainer;
		setTimeout(function(){
			$alertContainer.appendTo($alertsContainer);
			$alertContainer.show(fadeInTime,function(){
				setTimeout(function(){
					$alertContainer.hide(fadeOutTime);
				},displayTime);
			});		
		},delay);
	};
	
	AlertManager.prototype.enqueueAlert = function(message, type, delay, fadeInTime, displayTime, fadeOutTime){
		var messages = $.cookie(this.cookieName) || [];
		var message = {
			message : message,
			type : type
		};
		if (fadeInTime){
			message.fadeInTime = fadeInTime;
		}
		if (displayTime){
			message.displayTime = displayTime;
		}
		if (fadeOutTime){
			message.fadeOutTime = fadeOutTime;
		}
		if (delay!==undefined){
			message.delay = delay;
		} else {
			message.delay = 0;
		}
		messages.push(message);
		$.removeCookie(this.cookieName);
		$.cookie(this.cookieName,messages);
	};
	
	AlertManager.prototype.dequeueAlert = function(){
		var messages = $.cookie(this.cookieName) || [];
		if (messages.length==0){
			return;
		}
		var message = messages[0];
		this.displayAlert(message.message, message.type, message.delay, message.fadeInTime, message.displayTime, message.fadeOutTime);
		messages.splice(0,1);
		$.removeCookie(this.cookieName);
		if (messages.length > 0){
			$.cookie(this.cookieName,messages);
		}
	}
	
	AlertManager.prototype.dequeueAllAlerts = function(){
		var messages = $.cookie(this.cookieName) || [];
		if (messages.length==0){
			return;
		}
		for (var i=0;i<messages.length;i++){
			var message = messages[0];
			this.displayAlert(message.message, message.type, message.delay, message.fadeInTime, message.displayTime, message.fadeOutTime);
			messages.splice(0,1);			
		}
		$.removeCookie(this.cookieName);
	}
	
})(jQuery)