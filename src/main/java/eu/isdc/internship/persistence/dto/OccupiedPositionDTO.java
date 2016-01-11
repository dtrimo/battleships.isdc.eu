package eu.isdc.internship.persistence.dto;

/**
 * The Class OccupiedPositionDTO.
 */
public class OccupiedPositionDTO {

	private Long occupiedPositionId;
	private int x;
	private int y;
	private Long battleshipPositionId;

	/**
	 * Gets the occupied position id.
	 *
	 * @return the occupied position id
	 */
	public Long getOccupiedPositionId() {
		return occupiedPositionId;
	}

	/**
	 * Sets the occupied position id.
	 *
	 * @param occupiedPositionId
	 *            the new occupied position id
	 */
	public void setOccupiedPositionId(Long occupiedPositionId) {
		this.occupiedPositionId = occupiedPositionId;
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

}
