package eu.isdc.internship.persistence.dto;

import java.util.List;

/**
 * The Class GameTypeDTO.
 */
public class GameTypeDTO {

	private Long gameTypeId;
	private String name;
	private String shortDescription;
	private int n;
	private int m;
	private List<AvailableBattleshipDTO> availableBattleships;

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
	 *            the new n
	 */
	public void setN(int n) {
		this.n = n;
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
	 *            the new m
	 */
	public void setM(int m) {
		this.m = m;
	}

	/**
	 * Gets the available b ts.
	 *
	 * @return the available b ts
	 */
	public List<AvailableBattleshipDTO> getAvailableBTs() {
		return availableBattleships;
	}

	/**
	 * Sets the available b ts.
	 *
	 * @param availableBTs
	 *            the new available b ts
	 */
	public void setAvailableBTs(List<AvailableBattleshipDTO> availableBTs) {
		this.availableBattleships = availableBTs;
	}

	/**
	 * Gets the total ship cell count.
	 *
	 * @return the total ship cell count
	 */
	public int getTotalShipCellCount() {
		int count = 0;
		for (final AvailableBattleshipDTO battleship : availableBattleships) {
			count += battleship.getModel().getPositions().size();
		}
		return count;
	}
}
