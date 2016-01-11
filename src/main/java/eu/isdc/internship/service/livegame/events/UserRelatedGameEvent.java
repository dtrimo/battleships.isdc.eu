package eu.isdc.internship.service.livegame.events;

public class UserRelatedGameEvent implements GameEvent{
	
	protected Long userId;
	protected Long gameId;
	protected Long startConfigId;
	
	public UserRelatedGameEvent(){};
	public UserRelatedGameEvent(Long userId, Long gameId, Long startConfigId){
		this.userId = userId;
		this.gameId = gameId;
		this.startConfigId = startConfigId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getGameId() {
		return gameId;
	}
	
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	
	public Long getStartConfigId() {
		return startConfigId;
	}
	
	public void setStartConfigId(Long startConfigId) {
		this.startConfigId = startConfigId;
	}
	
}
