package eu.isdc.internship.beans;

import java.util.List;

import eu.isdc.internship.db.dto.BattleshipPositionDTO;

//The configuration of the ships of a user on the gameboard
public class StartConfig {

	private Integer userId;
	private List<BattleshipPositionDTO> selectedPositions;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<BattleshipPositionDTO> getSelectedPositions() {
		return selectedPositions;
	}

	public void setSelectedPositions(List<BattleshipPositionDTO> selectedPositions) {
		this.selectedPositions = selectedPositions;
	}

}
