package eu.isdc.internship.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * The Class BattleshipPosition represents the final position in which a user
 * has placed a battleship. It references the original model and stores how it
 * was translated, rotated and reflected. Also stores a list of occupied
 * positions for convenience.
 */
@Entity
@Table(name = "BATLLESHIP_POSITION")
public class BattleshipPosition {

	/** The battleship position id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "battleship_position_id", nullable = false)
	private Long battleshipPositionId;

	/** The translate x. */
	private Integer translateX;

	/** The translate y. */
	private Integer translateY;

	/** The transformations. */
	@OneToMany(mappedBy = "battleshipPosition", fetch = FetchType.LAZY, orphanRemoval = true) // 1-n
	@Cascade(value = CascadeType.ALL)
	private List<Transformation> transformations;

	/** The occupied positions. */
	@OneToMany(mappedBy = "battleshipPosition", fetch = FetchType.LAZY, orphanRemoval = true) // 1-n
	@Cascade(value = CascadeType.ALL)
	private List<OccupiedPosition> occupiedPositions;

	/** The start config. */
	@ManyToOne
	@JoinColumn(name = "start_config_id")
	private StartConfig startConfig;

	/** The available battleship. */
	@ManyToOne
	@JoinColumn(name = "available_battleship_id")
	private AvailableBattleship availableBattleship;

	/**
	 * Instantiates a new battleship position.
	 */
	public BattleshipPosition() {
	}

	/**
	 * Instantiates a new battleship position.
	 *
	 * @param transformations
	 *            the transformations
	 * @param translateX
	 *            the translate x
	 * @param translateY
	 *            the translate y
	 */
	public BattleshipPosition(List<Transformation> transformations, int translateX, int translateY) {
		this.transformations = new ArrayList<Transformation>();
		this.transformations.addAll(transformations);
		this.translateX = translateX;
		this.translateY = translateY;
	}

	/**
	 * Gets the start config.
	 *
	 * @return the start config
	 */
	public StartConfig getStartConfig() {
		return startConfig;
	}

	/**
	 * Sets the start config.
	 *
	 * @param startConfig
	 *            the new start config
	 */
	public void setStartConfig(StartConfig startConfig) {
		this.startConfig = startConfig;
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
	 * Gets the available battleship.
	 *
	 * @return the available battleship
	 */
	public AvailableBattleship getAvailableBattleship() {
		return availableBattleship;
	}

	/**
	 * Sets the available battleship.
	 *
	 * @param availableBattleship
	 *            the new available battleship
	 */
	public void setAvailableBattleship(AvailableBattleship availableBattleship) {
		this.availableBattleship = availableBattleship;
	}

	/**
	 * Gets the transformations.
	 *
	 * @return the transformations
	 */
	public List<Transformation> getTransformations() {
		return transformations;
	}

	/**
	 * Sets the transformations.
	 *
	 * @param transformations
	 *            the new transformations
	 */
	public void setTransformations(List<Transformation> transformations) {
		this.transformations = transformations;
	}

	/**
	 * Gets the occupied positions.
	 *
	 * @return the occupied positions
	 */
	public List<OccupiedPosition> getOccupiedPositions() {
		return occupiedPositions;
	}

	/**
	 * Sets the occupied positions.
	 *
	 * @param occupiedPositions
	 *            the new occupied positions
	 */
	public void setOccupiedPositions(List<OccupiedPosition> occupiedPositions) {
		this.occupiedPositions = occupiedPositions;
	}

}
