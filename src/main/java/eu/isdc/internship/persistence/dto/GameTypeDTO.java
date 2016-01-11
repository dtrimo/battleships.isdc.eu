package eu.isdc.internship.db.dto;

import java.util.List;

public class GameTypeDTO {
	private Long game_type_id;
	private String name;
	private String shortDescription;
	private int n;
	private int m;
	private List<GameDTO> games;
	private List<AvailableBattleshipDTO> availableBTs;
	
	public Long getGame_type_id() {
		return game_type_id;
	}
	public void setGame_type_id(Long game_type_id) {
		this.game_type_id = game_type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public List<GameDTO> getGames() {
		return games;
	}
	public void setGames(List<GameDTO> games) {
		this.games = games;
	}
	public List<AvailableBattleshipDTO> getAvailableBTs() {
		return availableBTs;
	}
	public void setAvailableBTs(List<AvailableBattleshipDTO> availableBTs) {
		this.availableBTs = availableBTs;
	}
}
