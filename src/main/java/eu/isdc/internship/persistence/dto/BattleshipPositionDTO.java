package eu.isdc.internship.persistence.dto;

import java.util.List;

/**
 * The Class BattleshipPositionDTO.
 */
public class BattleshipPositionDTO {

	private Long battleshipPositionId;
	private int translateX;
	private int translateY;
	private Long startConfigId;
	private Long availableBattleshipId;
	private List<TransformationDTO> transformations;
	private List<OccupiedPositionDTO> occupiedPositions;

	/**
	 * Gets the battleship position id.
	 *
	 * @return the battleship position id
	 */
	public Long getBattleshipPositionId() {
		return battleshipPositionId;
	}

	/**
	 * Sets the battleship position id.
	 *
	 * @param battleshipPositionId
	 *            the new battleship position id
	 */
	public void setBattleshipPositionId(Long battleshipPositionId) {
		this.battleshipPositionId = battleshipPositionId;
	}

	/**
	 * Gets the translate x.
	 *
	 * @return the translate x
	 */
	public int getTranslateX() {
		return translateX;
	}

	/**
	 * Sets the translate x.
	 *
	 * @param translateX
	 *            the new translate x
	 */
	public void setTranslateX(int translateX) {
		this.translateX = translateX;
	}

	/**
	 * Gets the translate y.
	 *
	 * @return the translate y
	 */
	public int getTranslateY() {
		return translateY;
	}

	/**
	 * Sets the translate y.
	 *
	 * @param translateY
	 *            the new translate y
	 */
	public void setTranslateY(int translateY) {
		this.translateY = translateY;
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
	 * Gets the transformations.
	 *
	 * @return the transformations
	 */
	public List<TransformationDTO> getTransformations() {
		return transformations;
	}

	/**
	 * Sets the transformations.
	 *
	 * @param transformations
	 *            the new transformations
	 */
	public void setTransformations(List<TransformationDTO> transformations) {
		this.transformations = transformations;
	}

	/**
	 * Gets the occupied positions.
	 *
	 * @return the occupied positions
	 */
	public List<OccupiedPositionDTO> getOccupiedPositions() {
		return occupiedPositions;
	}

	/**
	 * Sets the occupied positions.
	 *
	 * @param occupiedPositions
	 *            the new occupied positions
	 */
	public void setOccupiedPositions(List<OccupiedPositionDTO> occupiedPositions) {
		this.occupiedPositions = occupiedPositions;
	}
}
