package eu.isdc.internship.service.livegame.events;

public class UserSubmittedConfigScreenEvent extends UserRelatedGameEvent{

	public UserSubmittedConfigScreenEvent(){}
	
	public UserSubmittedConfigScreenEvent(Long userId, Long gameId, Long startConfigId){
		super(userId, gameId, startConfigId);
	}
	
}
