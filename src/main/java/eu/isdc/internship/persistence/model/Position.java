package eu.isdc.internship.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Position. Represents the coordinates of a cell in the makeup of a
 * battleship model.
 */
@Entity
@Table(name = "POSITIONS")
public class Position {

	/** The position id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "position_id", nullable = false)
	private Long positionId;

	/** The x. */
	@Column(name = "x", nullable = false)
	private int x;

	/** The y. */
	@Column(name = "y", nullable = false)
	private int y;

	/** The model. */
	@ManyToOne
	@JoinColumn(name = "battleship_model_id")
	private BattleshipModel battleshipModel;

	/**
	 * Instantiates a new position.
	 */
	public Position() {
	}

	/**
	 * Instantiates a new position.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the position id.
	 *
	 * @return the position_id
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
	public void setPositionId(final Long positionId) {
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
	 *            the x to set
	 */
	public void setX(final int x) {
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
	 *            the y to set
	 */
	public void setY(final int y) {
		this.y = y;
	}

	/**
	 * Gets the battleship model.
	 *
	 * @return the battleship model
	 */
	public BattleshipModel getBattleshipModel() {
		return battleshipModel;
	}

	/**
	 * Sets the battleship model.
	 *
	 * @param battleshipModel
	 *            the new battleship model
	 */
	public void setBattleshipModel(BattleshipModel battleshipModel) {
		this.battleshipModel = battleshipModel;
	}

}
