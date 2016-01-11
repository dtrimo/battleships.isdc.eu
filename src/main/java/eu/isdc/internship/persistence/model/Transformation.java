package eu.isdc.internship.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Transformation.
 */
@Entity
@Table(name = "TRANSFORMATIONS")
public class Transformation {

	/** The transformation id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transformation_id", nullable = false)
	private Long transformationId;

	/** The battleship position. */
	@ManyToOne
	@JoinColumn(name = "battleship_position_id")
	private BattleshipPosition battleshipPosition;

	/** The transformation order. */
	@Column
	private Integer transformationOrder;

	/** The type. */
	@Column
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
	 * Gets the battleship position.
	 *
	 * @return the battleship position
	 */
	public BattleshipPosition getBattleshipPosition() {
		return battleshipPosition;
	}

	/**
	 * Sets the battleship position.
	 *
	 * @param battleshipPosition
	 *            the new battleship position
	 */
	public void setBattleshipPosition(BattleshipPosition battleshipPosition) {
		this.battleshipPosition = battleshipPosition;
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
	 * Sets the ransformation order.
	 *
	 * @param transformationOrder
	 *            the new ransformation order
	 */
	public void setransformationOrder(Integer transformationOrder) {
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
