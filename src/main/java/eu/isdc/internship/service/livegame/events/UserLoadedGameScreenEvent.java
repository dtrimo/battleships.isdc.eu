package eu.isdc.internship.service.livegame.events;

public class UserLoadedGameScreenEvent extends UserRelatedGameEvent {

	public UserLoadedGameScreenEvent(){}
	
	public UserLoadedGameScreenEvent(Long userId, Long gameId, Long startConfigId){
		super(userId, gameId, startConfigId);
	}
	
}
