package eu.isdc.internship.db.dto;

import java.util.List;

public class BattleshipModelDTO {
	  private Long model_id;
	  private String name;
	  private List<PositionDTO> positions;
	  private List<AvailableBattleshipDTO> Av_BT;

	public Long getModel_id() {
		return model_id;
	}
	public void setModel_id(Long model_id) {
		this.model_id = model_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PositionDTO> getPositions() {
		return positions;
	}
	public void setPositions(List<PositionDTO> positions) {
		this.positions = positions;
	}
	public List<AvailableBattleshipDTO> getAv_BT() {
		return Av_BT;
	}
	public void setAv_BT(List<AvailableBattleshipDTO> av_BT) {
		Av_BT = av_BT;
	}
}
