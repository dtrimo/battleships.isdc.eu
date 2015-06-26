package eu.isdc.internship.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "MOVES")
public class Move {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MOVE_ID", nullable = false)
	private Long move_id;
	
	@Column(name = "ROUND", nullable = false)
	private int round;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "MOVING_DATE", nullable = false, length = 10)
	private Date date;

	@ManyToOne //n-1 cu Users
	@JoinColumn(name = "user_id")
	@Cascade(CascadeType.REFRESH)
	private User user;
	
	@ManyToOne //n-1 cu StartConfig
	@JoinColumn(name = "stargConfig_id")
	private StartConfig startConfig;
	
	@Column(name = "X", nullable = false)
	private int x;
	
	@Column(name = "Y", nullable = false)
	private int y;
	
	@ManyToOne //n-1 cu Games
	@JoinColumn(name = "game_id")
	private Game game;

	public Move(){}
	public Move(int rounds, Date d, int x, int y){
		this.date=d;
		this.round=rounds;
		this.x=x;
		this.y=y;
	}
	
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	
	
}
