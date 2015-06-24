package eu.isdc.internship.db.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import eu.isdc.internship.db.model.AvailableBattleship;
import eu.isdc.internship.db.model.Position;

public class BattleshipModelDTO {
	  private Long model_id;
	  private String name;
	  private List<PositionDTO> positions;
//	  private List<AvailableBattleship> Av_BT;

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
	  
	  

	  
}
