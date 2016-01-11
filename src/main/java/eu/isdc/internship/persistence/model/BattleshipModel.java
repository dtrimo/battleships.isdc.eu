package eu.isdc.internship.persistence.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * The Class BattleshipModel represents a kind of battleship. Basically, it is a
 * list of positions occupied by a battleship. The positions should all be in
 * the first quadrant (nonnegative coordinates) and at least one cell/position
 * should touch each coordinate axis (at the moment this is not enforced, but
 * correct functioning of the application relies on it).
 */
// TODO: Enforce that the battleship model touches the x and y axes and is in
// the first quadrant.
@Entity
@Table(name = "BATLLESHIP_MODELS")
public class BattleshipModel {

	/** The model id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "battleship_model_id", nullable = false)
	private Long modelId;

	/** The name. */
	@Column(name = "battleship_model_name")
	private String name;

	/**
	 * The positions represent the coordinates of the cells which the model
	 * contains.
	 */
	@OneToMany(mappedBy = "battleshipModel", fetch = FetchType.LAZY)
	@Cascade(value = CascadeType.ALL)
	private List<Position> positions;

	/** The available battleships. */
	@OneToMany(mappedBy = "battleshipModel", fetch = FetchType.LAZY)
	@Cascade(value = CascadeType.ALL)
	private List<AvailableBattleship> availableBattleships;

	/**
	 * Instantiates a new battleship model.
	 */
	public BattleshipModel() {
	}

	/**
	 * Instantiates a new battleship model.
	 *
	 * @param name
	 *            the name
	 */
	public BattleshipModel(String name) {
		this.name = name;
	}

	/**
	 * Gets the available battleships.
	 *
	 * @return the available battleships
	 */
	public List<AvailableBattleship> getAvailableBattleships() {
		return availableBattleships;
	}

	/**
	 * Sets the available battleships.
	 *
	 * @param availableBattleships
	 *            the new available battleships
	 */
	public void setAvailableBattleships(List<AvailableBattleship> availableBattleships) {
		this.availableBattleships = availableBattleships;
	}

	/**
	 * Gets the model id.
	 *
	 * @return the model id
	 */
	public Long getModelId() {
		return modelId;
	}

	/**
	 * Sets the model id.
	 *
	 * @param modelId
	 *            the new model id
	 */
	public void setModelId(final Long modelId) {
		this.modelId = modelId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the positions.
	 *
	 * @return the positions
	 */
	public List<Position> getPositions() {
		return positions;
	}

	/**
	 * Sets the positions.
	 *
	 * @param positions
	 *            the new positions
	 */
	public void setPositions(final List<Position> positions) {
		this.positions = positions;
	}

}
