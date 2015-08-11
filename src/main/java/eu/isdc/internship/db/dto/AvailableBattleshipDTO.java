package eu.isdc.internship.db.dto;

import java.util.List;

public class AvailableBattleshipDTO {
	private Long Av_BT_id;	
	private int count;
	private BattleshipModelDTO model;
	private List<BattleshipPositionDTO> BT_Positions;
	private Long gameTypeId;
	
	
	public Long getAv_BT_id() {
		return Av_BT_id;
	}
	public void setAv_BT_id(Long av_BT_id) {
		Av_BT_id = av_BT_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public BattleshipModelDTO getModel() {
		return model;
	}
	public void setModel(BattleshipModelDTO model) {
		this.model = model;
	}
	public List<BattleshipPositionDTO> getBT_Positions() {
		return BT_Positions;
	}
	public void setBT_Positions(List<BattleshipPositionDTO> bT_Positions) {
		BT_Positions = bT_Positions;
	}
	public Long getGameTypeId() {
		return gameTypeId;
	}
	public void setGameTypeId(Long gameTypeId) {
		this.gameTypeId = gameTypeId;
	}

}
