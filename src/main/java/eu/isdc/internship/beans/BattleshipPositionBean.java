package eu.isdc.internship.beans;

import java.util.List;

public class BattleshipPositionBean {

	private int xOffset;
	private int yOffset;
	private Long availableBattleshipId;
	private List<PositionBean> availableBattleshipPositions;
	private List<Transformation> transformations;
	private List<OccupiedPositionBean> occupiedPositions;

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public Long getAvailableBattleshipId() {
		return availableBattleshipId;
	}

	public void setAvailableBattleshipId(Long availableBattleshipId) {
		this.availableBattleshipId = availableBattleshipId;
	}

	public List<Transformation> getTransformations() {
		return transformations;
	}

	public void setTransformations(List<Transformation> transformations) {
		this.transformations = transformations;
	}

	public List<OccupiedPositionBean> getOccupiedPositions() {
		return occupiedPositions;
	}

	public void setOccupiedPositions(List<OccupiedPositionBean> occupiedPositions) {
		this.occupiedPositions = occupiedPositions;
	}

	public List<PositionBean> getAvailableBattleshipPositions() {
		return availableBattleshipPositions;
	}

	public void setAvailableBattleshipPositions(List<PositionBean> availableBattleshipPositions) {
		this.availableBattleshipPositions = availableBattleshipPositions;
	}

}
