package eu.isdc.internship.persistence.dto;

import java.util.Date;
import java.util.List;

/**
 * The Class GameDTO.
 */
public class GameDTO {

	private Long gameId;
	private Date date;
	private GameTypeDTO gameType;
	private List<StartConfigDTO> startConfigs;
	private List<MoveDTO> moves;

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

	/**
	 * Gets the game type.
	 *
	 * @return the game type
	 */
	public GameTypeDTO getGameType() {
		return gameType;
	}

	/**
	 * Sets the game type.
	 *
	 * @param gameType
	 *            the new game type
	 */
	public void setGameType(GameTypeDTO gameType) {
		this.gameType = gameType;
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
	 * Gets the start configs.
	 *
	 * @return the start configs
	 */
	public List<StartConfigDTO> getStartConfigs() {
		return startConfigs;
	}

	/**
	 * Sets the start configs.
	 *
	 * @param startConfigs
	 *            the new start configs
	 */
	public void setStartConfigs(List<StartConfigDTO> startConfigs) {
		this.startConfigs = startConfigs;
	}

	/**
	 * Gets the moves.
	 *
	 * @return the moves
	 */
	public List<MoveDTO> getMoves() {
		return moves;
	}

	/**
	 * Sets the moves.
	 *
	 * @param moves
	 *            the new moves
	 */
	public void setMoves(List<MoveDTO> moves) {
		this.moves = moves;
	}
}
