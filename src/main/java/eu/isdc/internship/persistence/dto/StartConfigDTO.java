package eu.isdc.internship.persistence.dto;

import java.util.List;

/**
 * The Class StartConfigDTO.
 */
public class StartConfigDTO {

	private Long startConfigId;
	private List<BattleshipPositionDTO> battleshipPositions;
	private Long gameId;
	private List<MoveDTO> moves;
	private Long userId;
	private String gameRole;

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
	 * Gets the battleship positions.
	 *
	 * @return the battleship positions
	 */
	public List<BattleshipPositionDTO> getBattleshipPositions() {
		return battleshipPositions;
	}

	/**
	 * Sets the b t_ positions.
	 *
	 * @param battleshipPositions
	 *            the new b t_ positions
	 */
	public void setBT_Positions(List<BattleshipPositionDTO> battleshipPositions) {
		this.battleshipPositions = battleshipPositions;
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
	 * Sets the battleship positions.
	 *
	 * @param battleshipPositions
	 *            the new battleship positions
	 */
	public void setBattleshipPositions(List<BattleshipPositionDTO> battleshipPositions) {
		this.battleshipPositions = battleshipPositions;
	}

	/**
	 * Gets the game role.
	 *
	 * @return the game role
	 */
	public String getGameRole() {
		return gameRole;
	}

	/**
	 * Sets the game role.
	 *
	 * @param gameRole
	 *            the new game role
	 */
	public void setGameRole(String gameRole) {
		this.gameRole = gameRole;
	}

}
