package eu.isdc.internship.beans;

public class GameBean {

	private Integer gameId;
	private Integer gametypeId;
	private StartConfig user1Config;
	private StartConfig user2Config;

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Integer getGametypeId() {
		return gametypeId;
	}

	public void setGametypeId(Integer gametypeId) {
		this.gametypeId = gametypeId;
	}

	public StartConfig getUser1Config() {
		return user1Config;
	}

	public void setUser1Config(StartConfig user1Config) {
		this.user1Config = user1Config;
	}

	public StartConfig getUser2Config() {
		return user2Config;
	}

	public void setUser2Config(StartConfig user2Config) {
		this.user2Config = user2Config;
	}

}
