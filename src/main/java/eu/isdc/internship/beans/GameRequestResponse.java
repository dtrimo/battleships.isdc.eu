package eu.isdc.internship.beans;

public class GameRequestResponse {

	private Integer gameId;
	private GameRole gameRole;
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public GameRole getGameRole() {
		return gameRole;
	}

	public void setGameRole(GameRole gameRole) {
		this.gameRole = gameRole;
	}



}
