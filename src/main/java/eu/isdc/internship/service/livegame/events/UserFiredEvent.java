package eu.isdc.internship.service.livegame.events;

import java.util.Date;

public class UserFiredEvent extends UserRelatedGameEvent {

	private Integer round;
	private Integer x;
	private Integer y;
	private Date timestamp;

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
