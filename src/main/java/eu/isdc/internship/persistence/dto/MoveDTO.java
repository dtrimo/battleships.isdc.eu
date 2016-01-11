package eu.isdc.internship.persistence.dto;

import java.util.Date;

/**
 * The Class MoveDTO.
 */
public class MoveDTO {

	private Long moveId;
	private int round;
	private Date date;
	private int x;
	private int y;
	private Long userId;
	private Long startConfigId;
	private Long gameId;

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
	 * Gets the start config id.
	 *
	 * @return the start config id
	 */
	public Long getStartConfigId() {
		return startConfigId;
	}

	/**
	 * Sets the start config id.
	 *
	 * @param startConfigId
	 *            the new start config id
	 */
	public void setStartConfigId(Long startConfigId) {
		this.startConfigId = startConfigId;
	}

	/**
	 * Gets the game id.
	 *
	 * @return the game id
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

}
