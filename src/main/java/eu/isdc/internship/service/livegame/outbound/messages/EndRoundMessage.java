package eu.isdc.internship.service.livegame.outbound.messages;

import eu.isdc.internship.beans.OccupiedPositionBean;

public class EndRoundMessage {

	private String type = "END_ROUND";
	private Long gameId;
	private Integer round;
	private OccupiedPositionBean enemyShot;
	private boolean gameEnded;
	private boolean won;

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public OccupiedPositionBean getEnemyShot() {
		return enemyShot;
	}

	public void setEnemyShot(OccupiedPositionBean enemyShot) {
		this.enemyShot = enemyShot;
	}

	public String getType() {
		return type;
	}

	public boolean isGameEnded() {
		return gameEnded;
	}

	public void setGameEnded(boolean gameEnded) {
		this.gameEnded = gameEnded;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public void setType(String type) {
		this.type = type;
	}

}
