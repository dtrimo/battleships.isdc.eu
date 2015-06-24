package eu.isdc.internship.db.dto;

import java.util.Date;


import eu.isdc.internship.db.model.Game;
import eu.isdc.internship.db.model.StartConfig;
import eu.isdc.internship.db.model.User;

public class MoveDTO {
	private Long move_id;
	private int round;
	private Date date;
	private User user;
	private StartConfig startConfig;
	private int x;
	private int y;	
//	private Game game;
	
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public StartConfig getStartConfig() {
		return startConfig;
	}
	public void setStartConfig(StartConfig startConfig) {
		this.startConfig = startConfig;
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
