package eu.isdc.internship.persistence.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * The Class Game.
 */
@Entity
@Table(name = "GAMES")
public class Game {

	/** The game id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "game_id", nullable = false)
	private Long gameId;

	/** The date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "playing_date", nullable = false, length = 10)
	private Date date;

	/** The game type. */
	@ManyToOne
	@JoinColumn(name = "game_type_id")
	private GameType gameType;

	/** The start configs. */
	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade(value = CascadeType.ALL)
	private List<StartConfig> startConfigs;

	/** The moves. */
	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade(value = CascadeType.ALL)
	private List<Move> moves;

	/** The winner. */
	@ManyToOne
	@JoinColumn(name = "winner_user_id")
	private User winner;

	/** The finished. */
	@Column(name = "finished")
	private boolean finished;

	/**
	 * Instantiates a new game.
	 */
	public Game() {
	}

	/**
	 * Instantiates a new game.
	 *
	 * @param date
	 *            the date
	 */
	public Game(Date date) {
		this.date = date;
	}

	/**
	 * Gets the game id.
	 *
	 * @return the game_id
	 */
	public Long getGameId() {
		return gameId;
	}

	/**
	 * Sets the game id.
	 *
	 * @param gameId
	 *            the new game id
	 */
	public void setGameId(Long gameId) {
		this.gameId = gameId;
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
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the start configs.
	 *
	 * @return the startConfigs
	 */
	public List<StartConfig> getStartConfigs() {
		return startConfigs;
	}

	/**
	 * Sets the start configs.
	 *
	 * @param startConfigs
	 *            the startConfigs to set
	 */
	public void setStartConfigs(List<StartConfig> startConfigs) {
		this.startConfigs = startConfigs;
	}

	/**
	 * Gets the moves.
	 *
	 * @return the moves
	 */
	public List<Move> getMoves() {
		return moves;
	}

	/**
	 * Sets the moves.
	 *
	 * @param moves
	 *            the moves to set
	 */
	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}

	/**
	 * Gets the game type.
	 *
	 * @return the game type
	 */
	public GameType getGameType() {
		return gameType;
	}

	/**
	 * Sets the game type.
	 *
	 * @param gameType
	 *            the new game type
	 */
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public User getWinner() {
		return winner;
	}

	/**
	 * Sets the winner.
	 *
	 * @param winner
	 *            the new winner
	 */
	public void setWinner(User winner) {
		this.winner = winner;
	}

	/**
	 * Checks if is finished.
	 *
	 * @return true, if is finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Sets the finished.
	 *
	 * @param finished
	 *            the new finished
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
