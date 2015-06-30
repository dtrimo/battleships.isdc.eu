package eu.isdc.internship.db.dto;

import java.util.Date;

public class MoveDTO {
	private Long move_id;
	private int round;
	private Date date;
	private UserDTO user;
	private StartConfigDTO startConfig;
	private int x;
	private int y;	
	private GameDTO game;
	
	public Long getMove_id() {
		return move_id;
	}
	public void setMove_id(Long move_id) {
		this.move_id = move_id;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public StartConfigDTO getStartConfig() {
		return startConfig;
	}
	public void setStartConfig(StartConfigDTO startConfig) {
		this.startConfig = startConfig;
	}
	public GameDTO getGame() {
		return game;
	}
	public void setGame(GameDTO game) {
		this.game = game;
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
	
	
}
