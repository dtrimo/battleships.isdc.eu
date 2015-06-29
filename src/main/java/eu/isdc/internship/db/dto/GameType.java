package eu.isdc.internship.db.dto;

import eu.isdc.internship.db.model.Game;

public class GameType {
	private Long game_type_id;
	private int n;
	private int m;
	private Game game;
	
	public Long getGame_type_id() {
		return game_type_id;
	}
	public void setGame_type_id(Long game_type_id) {
		this.game_type_id = game_type_id;
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
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
