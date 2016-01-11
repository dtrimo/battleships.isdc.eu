package eu.isdc.internship.persistence.dto;

/**
 * The Class PositionDTO.
 */
public class PositionDTO {

	private Long positionId;
	private int x;
	private int y;
	private Long battleshipModelId;

	/**
	 * Gets the position id.
	 *
	 * @return the position id
	 */
	public Long getPositionId() {
		return positionId;
	}

	/**
	 * Sets the position id.
	 *
	 * @param positionId
	 *            the new position id
	 */
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
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
	 * Gets the battleship model id.
	 *
	 * @return the battleship model id
	 */
	public Long getBattleshipModelId() {
		return battleshipModelId;
	}

	/**
	 * Sets the battleship model id.
	 *
	 * @param battleshipModelId
	 *            the new battleship model id
	 */
	public void setBattleshipModelId(Long battleshipModelId) {
		this.battleshipModelId = battleshipModelId;
	}

}
