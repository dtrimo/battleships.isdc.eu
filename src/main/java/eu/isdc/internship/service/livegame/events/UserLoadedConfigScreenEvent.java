package eu.isdc.internship.service.livegame.events;

public class UserLoadedConfigScreenEvent extends UserRelatedGameEvent {
	
	public UserLoadedConfigScreenEvent(){}
	
	public UserLoadedConfigScreenEvent(Long userId, Long gameId, Long startConfigId){
		super(userId, gameId, startConfigId);
	}
	
}
