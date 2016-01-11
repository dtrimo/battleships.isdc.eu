package eu.isdc.internship.persistence.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class StatisticsDTO.
 */

/**
 * @author Daniel
 *
 */
public class StatisticsDTO {

	@JsonIgnore
	private Long statisticsId;
	@JsonIgnore
	private Long userId;
	private int nrOfWins;
	private int nrOfPlayedGames;
	private int nrOfRoundsToWin;
	private int nrOfRundsToLose;

	/**
	 * Gets the statistics id.
	 *
	 * @return the statistics id
	 */
	public Long getStatisticsId() {
		return statisticsId;
	}

	/**
	 * Sets the statistics id.
	 *
	 * @param statisticsId
	 *            the new statistics id
	 */
	public void setStatisticsId(Long statisticsId) {
		this.statisticsId = statisticsId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
