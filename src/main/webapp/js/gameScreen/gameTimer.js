define(['jquery'], function($){
	
	var forceDigits = function(number, digits){
		return Number(number).toLocaleString('en-US', {minimumIntegerDigits: digits, useGrouping:false});
	};
	
	var formatTime = function(time){
		return forceDigits(Math.floor(time/60),2) + ":"+ forceDigits(time%60,2);
	};
	
	/**
	 * default_config = {
	 * 	roundLength : 20,
	 *  containerSelector : "body",
	 *  roundChangeEvent : "roundChange"
	 * }
	 */
	var GameTimer = function(config){
		config = config || {};
		this.currentRound = 0;
		this.elapsedTime = 0;
		this.roundLength = config.roundLength || 20;
		this.autoIncrementRound = !!config.autoIncrementRound;
		
		this.globalTimer = null;
		this.currentRoundTimer = null;
		this.remainingTimeForRound = this.roundLength;
		
		this.$container = $(config.containerSelector || "body");
		this.$elapsedTimeSpan = this.$container.find("span.elapsed-time");
		this.$currentRoundSpan = this.$container.find("span.round");
		this.$remainingTimeSpan = this.$container.find("span.remaining-time");
		this.roundChangeEvent = config.roundChangeEvent || "roundChange";
	}
	
	GameTimer.prototype.start = function(){
		var self = this;
		self.globalTimer = setInterval(function(){
			self.elapsedTime++;
			self.$elapsedTimeSpan.text(formatTime(self.elapsedTime));
		},1000);
		self.incrementRound();
	};
	
	GameTimer.prototype.incrementRound = function(){
		var self = this;
		this.currentRound += 1;
		this.$currentRoundSpan.text(this.currentRound);
		if (this.currentRoundTimer){
			clearInterval(this.currentRoundTimer);
		}
		this.remainingTimeForRound = this.roundLength;
		this.currentRoundTimer = setInterval(function(){
			if (self.remainingTimeForRound > 0){
				self.remainingTimeForRound -= 1;
				self.$remainingTimeSpan.text(formatTime(self.remainingTimeForRound));				
			} 
		},1000);
		this.$remainingTimeSpan.text(formatTime(this.remainingTimeForRound));
		$(document).trigger(this.roundChangeEvent);
	};
	
	return GameTimer;
	
});