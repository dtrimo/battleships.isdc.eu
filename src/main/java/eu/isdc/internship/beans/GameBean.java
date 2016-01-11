package eu.isdc.internship.beans;

public class GameBean {

	private Integer gameId;
	private Integer gametypeId;
	private StartConfigBean user1Config;
	private StartConfigBean user2Config;

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

	public StartConfigBean getUser1Config() {
		return user1Config;
	}

	public void setUser1Config(StartConfigBean user1Config) {
		this.user1Config = user1Config;
	}

	public StartConfigBean getUser2Config() {
		return user2Config;
	}

	public void setUser2Config(StartConfigBean user2Config) {
		this.user2Config = user2Config;
	}

}
