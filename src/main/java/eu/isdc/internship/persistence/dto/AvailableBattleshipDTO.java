package eu.isdc.internship.persistence.dto;

/**
 * The Class AvailableBattleshipDTO.
 */
public class AvailableBattleshipDTO {

	private Long availableBattleshipId;
	private BattleshipModelDTO model;
	private Long gameTypeId;

	/**
	 * Gets the available battleship id.
	 *
	 * @return the available battleship id
	 */
	public Long getAvailableBattleshipId() {
		return availableBattleshipId;
	}

	/**
	 * Sets the available battleship id.
	 *
	 * @param availableBattleshipId
	 *            the new available battleship id
	 */
	public void setAvailableBattleshipId(Long availableBattleshipId) {
		this.availableBattleshipId = availableBattleshipId;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public BattleshipModelDTO getModel() {
		return model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *            the new model
	 */
	public void setModel(BattleshipModelDTO model) {
		this.model = model;
	}

	/**
	 * Gets the game type id.
	 *
	 * @return the game type id
	 */
	public Long getGameTypeId() {
		return gameTypeId;
	}

	/**
	 * Sets the game type id.
	 *
	 * @param gameTypeId
	 *            the new game type id
	 */
	public void setGameTypeId(Long gameTypeId) {
		this.gameTypeId = gameTypeId;
	}

}
