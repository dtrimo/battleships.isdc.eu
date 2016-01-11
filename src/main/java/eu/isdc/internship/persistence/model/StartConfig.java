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
 * The Class StartConfig. Holds the list of BattleshipPositions on which a user
 * has placed his battleships in the configuration phase of a game. Also holds
 * the list of moves the user subsequently makes.
 */
@Entity
@Table(name = "START_CONFIG")
public class StartConfig {

	/** The start config id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "start_config_id", nullable = false)
	private Long startConfigId;

	/** The battleship positions. */
	@OneToMany(mappedBy = "startConfig", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	private List<BattleshipPosition> battleshipPositions;

	/** The game. */
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

	/** The move. */
	@OneToMany(mappedBy = "startConfig", fetch = FetchType.LAZY) // 1-n cu Moves
	@Cascade(value = CascadeType.ALL)
	private List<Move> moves;

	/** The user. */
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/** The user. */
	@Column(name = "game_role")
	private String gameRole;

	/**
	 * Instantiates a new start config.
	 */
	public StartConfig() {
	}

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
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Sets the game.
	 *
	 * @param game
	 *            the new game
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Gets the move.
	 *
	 * @return the move
	 */
	public List<Move> getMoves() {
		return moves;
	}

	/**
	 * Sets the move.
	 *
	 * @param move
	 *            the new move
	 */
	public void setMoves(List<Move> move) {
		this.moves = move;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the start config id.
	 *
	 * @return the startConfig_id
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
	 * Gets the game role.
	 *
	 * @return the game role
	 */
	public String getGameRole() {
		return gameRole;
	}

	/**
	 * Sets the game role.
	 *
	 * @param gameRole
	 *            the new game role
	 */
	public void setGameRole(String gameRole) {
		this.gameRole = gameRole;
	}

}
