package eu.isdc.internship.persistence.model;

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

/**
 * The Class Move. Represents a position on which a user has fired during a
 * round of a game.
 */
@Entity
@Table(name = "MOVES")
public class Move {

	/** The move id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "move_id", nullable = false)
	private Long moveId;

	/** The round. */
	@Column(name = "round", nullable = false)
	private int round;

	/** The date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "moving_date", nullable = false, length = 10)
	private Date date;

	/** The user. */
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/** The start config. */
	@ManyToOne
	@JoinColumn(name = "start_config_id")
	private StartConfig startConfig;

	/** The x. */
	@Column(name = "x", nullable = false)
	private int x;

	/** The y. */
	@Column(name = "y", nullable = false)
	private int y;

	/** The game. */
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

	/**
	 * Instantiates a new move.
	 */
	public Move() {
	}

	/**
	 * Instantiates a new move.
	 *
	 * @param rounds
	 *            the rounds
	 * @param date
	 *            the date
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public Move(int rounds, Date date, int x, int y) {
		this.date = date;
		this.round = rounds;
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the move id.
	 *
	 * @return the move id
	 */
	public Long getMoveId() {
		return moveId;
	}

	/**
	 * Sets the move id.
	 *
	 * @param moveId
	 *            the new move id
	 */
	public void setMoveId(Long moveId) {
		this.moveId = moveId;
	}

	/**
	 * Gets the round.
	 *
	 * @return the round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * Sets the round.
	 *
	 * @param round
	 *            the new round
	 */
	public void setRound(int round) {
		this.round = round;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the start config.
	 *
	 * @return the start config
	 */
	public StartConfig getStartConfig() {
		return startConfig;
	}

	/**
	 * Sets the start config.
	 *
	 * @param startConfig
	 *            the new start config
	 */
	public void setStartConfig(StartConfig startConfig) {
		this.startConfig = startConfig;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x
	 *            the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y
	 *            the new y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Sets the game.
	 *
	 * @param game
	 *            the new game
	 */
	public void setGame(Game game) {
		this.game = game;
	}
}
