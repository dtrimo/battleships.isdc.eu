package eu.isdc.internship.service.livegame.events;

public class UserLeftGameEvent extends UserRelatedGameEvent{

	public UserLeftGameEvent(){}
	
	public UserLeftGameEvent(Long userId, Long gameId, Long startConfigId){
		super(userId, gameId, startConfigId);
	}
	
}
