package eu.isdc.internship.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class Statistics.
 */
@Entity
@Table(name = "USER_STATS")
public class Statistics {

	/** The stats id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stats_id", nullable = false)
	private Long statsId;

	/** The user. */
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	/** The nr of wins. */
	@Column(name = "nr_wins")
	private int nrOfWins;

	/** The nr of played games. */
	@Column(name = "nr_played_games")
	private int nrOfPlayedGames;

	/** The nr of rounds to win. */
	@Column(name = "nr_rounds_to_win")
	private int nrOfRoundsToWin;

	/** The nr of runds to lose. */
	@Column(name = "nr_rounds_to_lose")
	private int nrOfRundsToLose;

	/**
	 * Instantiates a new statistics.
	 */
	public Statistics() {
	}

	/**
	 * Instantiates a new statistics.
	 *
	 * @param wins
	 *            the wins
	 * @param played
	 *            the played
	 * @param roundsWin
	 *            the rounds win
	 * @param roundsLose
	 *            the rounds lose
	 */
	public Statistics(int wins, int played, int roundsWin, int roundsLose) {
		this.nrOfWins = wins;
		this.nrOfPlayedGames = played;
		this.nrOfRoundsToWin = roundsWin;
		this.nrOfRundsToLose = roundsLose;
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
	 * Gets the stats id.
	 *
	 * @return the stats id
	 */
	public Long getStatsId() {
		return statsId;
	}

	/**
	 * Sets the stats id.
	 *
	 * @param statsId
	 *            the new stats id
	 */
	public void setStatsId(Long statsId) {
		this.statsId = statsId;
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
	 * Gets the nr of wins.
	 *
	 * @return the nr of wins
	 */
	public int getNrOfWins() {
		return nrOfWins;
	}

	/**
	 * Sets the nr of wins.
	 *
	 * @param nrOfWins
	 *            the new nr of wins
	 */
	public void setNrOfWins(int nrOfWins) {
		this.nrOfWins = nrOfWins;
	}

	/**
	 * Gets the nr of played games.
	 *
	 * @return the nr of played games
	 */
	public int getNrOfPlayedGames() {
		return nrOfPlayedGames;
	}

	/**
	 * Sets the nr of played games.
	 *
	 * @param nrOfPlayedGames
	 *            the new nr of played games
	 */
	public void setNrOfPlayedGames(int nrOfPlayedGames) {
		this.nrOfPlayedGames = nrOfPlayedGames;
	}

	/**
	 * Gets the nr of rounds to win.
	 *
	 * @return the nr of rounds to win
	 */
	public int getNrOfRoundsToWin() {
		return nrOfRoundsToWin;
	}

	/**
	 * Sets the nr of rounds to win.
	 *
	 * @param nrOfRoundsToWin
	 *            the new nr of rounds to win
	 */
	public void setNrOfRoundsToWin(int nrOfRoundsToWin) {
		this.nrOfRoundsToWin = nrOfRoundsToWin;
	}

	/**
	 * Gets the nr of runds to lose.
	 *
	 * @return the nr of runds to lose
	 */
	public int getNrOfRundsToLose() {
		return nrOfRundsToLose;
	}

	/**
	 * Sets the nr of runds to lose.
	 *
	 * @param nrOfRundsToLose
	 *            the new nr of runds to lose
	 */
	public void setNrOfRundsToLose(int nrOfRundsToLose) {
		this.nrOfRundsToLose = nrOfRundsToLose;
	}
}
