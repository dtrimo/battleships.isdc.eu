package eu.isdc.internship.beans;

public class AvailableBattleshipModelBean {

	private Long gameTypeId;
	private Long availableBattleshipModelId;
	private BattleshipModelBean battleshipModel;
	
	public Long getGameTypeId() {
		return gameTypeId;
	}

	public void setGameTypeId(Long gameTypeId) {
		this.gameTypeId = gameTypeId;
	}

	public BattleshipModelBean getBattleshipModel() {
		return battleshipModel;
	}

	public void setBattleshipModel(BattleshipModelBean battleshipModel) {
		this.battleshipModel = battleshipModel;
	}

	public Long getAvailableBattleshipModelId() {
		return availableBattleshipModelId;
	}

	public void setAvailableBattleshipModelId(Long availableBattleshipModelId) {
		this.availableBattleshipModelId = availableBattleshipModelId;
	}

}
