package eu.isdc.internship.db.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import eu.isdc.internship.db.model.BattleshipPosition;
import eu.isdc.internship.db.model.Game;
import eu.isdc.internship.db.model.Move;
import eu.isdc.internship.db.model.User;

public class StartConfigDTO {
	private Long startConfig_id;
	private List<BattleshipPositionDTO> BT_Positions;
	private Game game;
//	private List<Move> move;	
	private User user;
	
	
	public Long getStartConfig_id() {
		return startConfig_id;
	}
	public void setStartConfig_id(Long startConfig_id) {
		this.startConfig_id = startConfig_id;
	}
	public List<BattleshipPositionDTO> getBT_Positions() {
		return BT_Positions;
	}
	public void setBT_Positions(List<BattleshipPositionDTO> bT_Positions) {
		BT_Positions = bT_Positions;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
