package eu.isdc.internship.beans;

public class GameRequestResponse {

	private Long gameId;
	private GameRole gameRole;
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	private Long startConfigId;

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public GameRole getGameRole() {
		return gameRole;
	}

	public void setGameRole(GameRole gameRole) {
		this.gameRole = gameRole;
	}

	public Long getStartConfigId() {
		return startConfigId;
	}

	public void setStartConfigId(Long startConfigId) {
		this.startConfigId = startConfigId;
	}

}
