/**
 * 
 * @author Dragos.Florea
 * TransformationDTO class - Data Transfer Object used to store Tranformation data
 */

package eu.isdc.internship.persistence.dto;

/**
 * The Class TransformationDTO.
 */
public class TransformationDTO {

	private Long transformationId;
	private Long battleshipPositionId;
	private Integer transformationOrder;
	private String type;

	/**
	 * Gets the transformation id.
	 *
	 * @return the transformation id
	 */
	public Long getTransformationId() {
		return transformationId;
	}

	/**
	 * Sets the transformation id.
	 *
	 * @param transformationId
	 *            the new transformation id
	 */
	public void setTransformationId(Long transformationId) {
		this.transformationId = transformationId;
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

	/**
	 * Gets the transformation order.
	 *
	 * @return the transformation order
	 */
	public Integer getTransformationOrder() {
		return transformationOrder;
	}

	/**
	 * Sets the transformation order.
	 *
	 * @param transformationOrder
	 *            the new transformation order
	 */
	public void setTransformationOrder(Integer transformationOrder) {
		this.transformationOrder = transformationOrder;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

}
