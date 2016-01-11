package eu.isdc.internship.db.dto;

public class PositionDTO {
	private Long position_id;	  
	private int x;
	private int y;
	private BattleshipModelDTO model;
	    
	public Long getPosition_id() {
		return position_id;
	}
	public void setPosition_id(Long position_id) {
		this.position_id = position_id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public BattleshipModelDTO getModel() {
		return model;
	}
	public void setModel(BattleshipModelDTO model) {
		this.model = model;
	}
}
