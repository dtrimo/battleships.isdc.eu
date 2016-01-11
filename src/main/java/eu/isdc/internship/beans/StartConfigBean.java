package eu.isdc.internship.beans;

import java.util.List;

import eu.isdc.internship.persistence.dto.BattleshipPositionDTO;

//The configuration of the ships of a user on the gameboard
public class StartConfigBean {

	private Long userId;
	private Long gameId;
	private GameRole gameRole;
	private Long startConfigId;
	private List<BattleshipPositionBean> selectedPositions;

	public List<BattleshipPositionBean> getSelectedPositions() {
		return selectedPositions;
	}

	public void setSelectedPositions(List<BattleshipPositionBean> selectedPositions) {
		this.selectedPositions = selectedPositions;
	}

	public GameRole getGameRole() {
		return gameRole;
	}

	public void setGameRole(GameRole gameRole) {
		this.gameRole = gameRole;
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
