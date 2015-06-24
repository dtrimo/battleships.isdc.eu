package eu.isdc.internship.db.dto;

import java.util.List;


import eu.isdc.internship.db.model.BattleshipModel;
import eu.isdc.internship.db.model.BattleshipPosition;
import eu.isdc.internship.db.model.Game;

public class AvailableBattleshipDTO {
	private Long Av_BT_id;	
	private int count;
	private BattleshipModel model;
	private List<BattleshipPositionDTO> BT_Positions;
//	private Game game;
	
	
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
	public BattleshipModel getModel() {
		return model;
	}
	public void setModel(BattleshipModel model) {
		this.model = model;
	}
	public List<BattleshipPositionDTO> getBT_Positions() {
		return BT_Positions;
	}
	public void setBT_Positions(List<BattleshipPositionDTO> bT_Positions) {
		BT_Positions = bT_Positions;
	}
	
	
}
