define(['jquery','jquery.cookie'],function($){
	
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
		this.animationMethods = ['fadeIn','fadeOut'];
	};
	
	AlertManager.prototype.setAnimationMethod = function(animationMethod){
		if (animationMethod == 'show'){
			this.animationMethods = ['show','hide'];
			return true;
		} else if (animationMethod == 'fade'){
			this.animationMethods = ['fadeIn', 'fadeOut'];
			return true;
		}
		return false;
	}
	
	AlertManager.prototype.displayAlert = function(message, type, delay, fadeInTime, displayTime, fadeOutTime){
		type = type || "success";
		fadeInTime = fadeInTime || 1000;
		displayTime = displayTime || 5000;
		fadeOutTime = fadeOutTime || 1000;
		if (delay==undefined){
			delay = 0;
		}
		
		var $alertContainer = $("<div class='alert "+type+"'></div>");
		var $message = $("<span></span>");
		$message.text(message);
		$message.appendTo($alertContainer);
		var $alertsContainer = this.$alertsContainer;
		var manager = this;
		setTimeout(function(){
			$alertContainer.appendTo($alertsContainer);
			$alertContainer[manager.animationMethods[0]](fadeInTime,function(){
				setTimeout(function(){
					$alertContainer[manager.animationMethods[1]](fadeOutTime);
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
	
	AlertManager.prototype.destroy = function(){
		$.removeCookie(this.cookieName);
		this.$alertsContainer.remove();
	}
	
	return (function(){
		var instances = {};
				
		return {
			getInstance : function(name, container){
				if (!instances[name]){
					instances[name] = new AlertManager(container, name);
				}
				return instances[name];
			},
			destroyInstance : function(name){
				if (instances[name]){
					instances[name].destroy();
				}
				delete instances[name];
			}
		}
		
	})();
	
});