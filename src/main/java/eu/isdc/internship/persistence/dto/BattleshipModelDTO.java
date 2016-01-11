package eu.isdc.internship.persistence.dto;

import java.util.List;

/**
 * The Class BattleshipModelDTO.
 */
public class BattleshipModelDTO {

	private Long modelId;
	private String name;
	private List<PositionDTO> positions;

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
	public void setModelId(Long modelId) {
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
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the positions.
	 *
	 * @return the positions
	 */
	public List<PositionDTO> getPositions() {
		return positions;
	}

	/**
	 * Sets the positions.
	 *
	 * @param positions
	 *            the new positions
	 */
	public void setPositions(List<PositionDTO> positions) {
		this.positions = positions;
	}

}
