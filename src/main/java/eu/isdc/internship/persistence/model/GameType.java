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
 * The Class GameType. Designates a collection of available battleships in a
 * game played on a board of size m*n.
 */
@Entity
@Table(name = "GAME_TYPES")
public class GameType {

	/** The game type id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "game_type_id", nullable = false)
	private Long gameTypeId;

	/** The name. */
	@Column(name = "game_type_name")
	private String name;

	/** The short description. */
	@Column(name = "short_description")
	private String shortDescription;

	/** The width of the board. */
	@Column(name = "m", nullable = false)
	private int m;

	/** The height of the board. */
	@Column(name = "n", nullable = false)
	private int n;

	/** The games. */
	@OneToMany(mappedBy = "gameType", fetch = FetchType.LAZY)
	@Cascade(value = CascadeType.ALL)
	private List<Game> games;

	/** The available battleships. */
	@OneToMany(mappedBy = "gameType", fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade(value = CascadeType.ALL)
	private List<AvailableBattleship> availableBattleships;

	/**
	 * Instantiates a new game type.
	 */
	public GameType() {
	}

	/**
	 * Instantiates a new game type.
	 *
	 * @param m
	 *            the m
	 * @param n
	 *            the n
	 */
	public GameType(int m, int n) {
		this.m = m;
		this.n = n;
	}

	/**
	 * Instantiates a new game type.
	 *
	 * @param name
	 *            the name
	 * @param shortDescription
	 *            the short description
	 * @param m
	 *            the m
	 * @param n
	 *            the n
	 */
	public GameType(String name, String shortDescription, int m, int n) {
		this.name = name;
		this.shortDescription = shortDescription;
		this.m = m;
		this.n = n;
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
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the short description.
	 *
	 * @return the short description
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * Sets the short description.
	 *
	 * @param shortDescription
	 *            the new short description
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * Gets the game type id.
	 *
	 * @return the game type id
	 */
	public Long getGameTypeId() {
		return gameTypeId;
	}

	/**
	 * Sets the game type id.
	 *
	 * @param gameTypeId
	 *            the new game type id
	 */
	public void setGameTypeId(Long gameTypeId) {
		this.gameTypeId = gameTypeId;
	}

	/**
	 * Gets the games.
	 *
	 * @return the games
	 */
	public List<Game> getGames() {
		return games;
	}

	/**
	 * Sets the games.
	 *
	 * @param games
	 *            the new games
	 */
	public void setGames(List<Game> games) {
		this.games = games;
	}

	/**
	 * Gets the m.
	 *
	 * @return the m
	 */
	public int getM() {
		return m;
	}

	/**
	 * Sets the m.
	 *
	 * @param m
	 *            the m to set
	 */
	public void setM(int m) {
		this.m = m;
	}

	/**
	 * Gets the n.
	 *
	 * @return the n
	 */
	public int getN() {
		return n;
	}

	/**
	 * Sets the n.
	 *
	 * @param n
	 *            the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}

	/**
	 * Gets the available b ts.
	 *
	 * @return the availableBTs
	 */
	public List<AvailableBattleship> getAvailableBTs() {
		return availableBattleships;
	}

	/**
	 * Sets the available b ts.
	 *
	 * @param availableBTs
	 *            the availableBTs to set
	 */
	public void setAvailableBTs(List<AvailableBattleship> availableBTs) {
		this.availableBattleships = availableBTs;
	}
}
