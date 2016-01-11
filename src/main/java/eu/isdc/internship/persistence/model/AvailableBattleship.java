package eu.isdc.internship.persistence.model;

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
 * The class AvailableBattleship represents a battleship model that is available
 * in a certain game type (it is the association class between battleship models
 * and game types). If a model appears multiple times in a game type, each
 * occurrence should correspond to a separate AvailableBattleship entry.
 */
@Entity
@Table(name = "AVAILABLE_BATTLESHIPS")
public class AvailableBattleship {

	/** The available battleship id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "available_battleship_id", nullable = false)
	private Long availableBattleshipId;

	/** The model. */
	@ManyToOne
	@JoinColumn(name = "battleship_model_id")
	private BattleshipModel battleshipModel;

	/** The battleship positions. */
	@OneToMany(mappedBy = "availableBattleship", fetch = FetchType.LAZY)
	@Cascade(value = CascadeType.ALL)
	private List<BattleshipPosition> battleshipPositions;

	/** The game type. */
	@ManyToOne
	@JoinColumn(name = "game_type_id")
	private GameType gameType;

	/**
	 * Gets the battleship positions.
	 *
	 * @return the battleship positions
	 */
	public List<BattleshipPosition> getBattleshipPositions() {
		return battleshipPositions;
	}

	/**
	 * Sets the battleship positions.
	 *
	 * @param battleshipPositions
	 *            the new battleship positions
	 */
	public void setBattleshipPositions(List<BattleshipPosition> battleshipPositions) {
		this.battleshipPositions = battleshipPositions;
	}

	/**
	 * Gets the game type.
	 *
	 * @return the game type
	 */
	public GameType getGameType() {
		return gameType;
	}

	/**
	 * Sets the game type.
	 *
	 * @param gameType
	 *            the new game type
	 */
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public BattleshipModel getBattleshipModel() {
		return battleshipModel;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *            the new model
	 */
	public void setBattleshipModel(BattleshipModel model) {
		this.battleshipModel = model;
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

}
