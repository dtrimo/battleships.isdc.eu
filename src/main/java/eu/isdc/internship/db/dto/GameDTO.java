package eu.isdc.internship.db.dto;

import java.util.Date;
import java.util.List;


import eu.isdc.internship.db.model.AvailableBattleship;
import eu.isdc.internship.db.model.Move;
import eu.isdc.internship.db.model.StartConfig;

public class GameDTO {
	private Long game_id;
	private Date date;
	private int m;
	private int n;
	private List<StartConfigDTO> startConfigs;
	private List<AvailableBattleshipDTO> availableBTs;
	private List<MoveDTO> moves;
	
	
	public Long getGame_id() {
		return game_id;
	}
	public void setGame_id(Long game_id) {
		this.game_id = game_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public List<StartConfigDTO> getStartConfigs() {
		return startConfigs;
	}
	public void setStartConfigs(List<StartConfigDTO> startConfigs) {
		this.startConfigs = startConfigs;
	}
	public List<AvailableBattleshipDTO> getAvailableBTs() {
		return availableBTs;
	}
	public void setAvailableBTs(List<AvailableBattleshipDTO> availableBTs) {
		this.availableBTs = availableBTs;
	}
	public List<MoveDTO> getMoves() {
		return moves;
	}
	public void setMoves(List<MoveDTO> moves) {
		this.moves = moves;
	}

	

	
}
