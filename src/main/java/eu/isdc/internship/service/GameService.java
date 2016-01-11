package eu.isdc.internship.service;

import java.util.List;

import eu.isdc.internship.beans.GameTypeBean;
import eu.isdc.internship.beans.StartConfigBean;
import eu.isdc.internship.persistence.dto.GameDTO;

/**
 * The Interface GameService.
 */
public interface GameService {

	/**
	 * Creates the game.
	 *
	 * @param gametypeId
	 *            the gametype id
	 * @param userIds
	 *            the user ids
	 * @return the game dto
	 */
	GameDTO createGame(Long gametypeId, List<Long> userIds);

	/**
	 * Gets the game.
	 *
	 * @param gameId
	 *            the game id
	 * @return the game
	 */
	GameDTO getGame(Long gameId);

	/**
	 * Submit start configuration.
	 *
	 * @param startConfig
	 *            the start config
	 */
	void submitStartConfiguration(StartConfigBean startConfig);

	/**
	 * Gets the start configuration.
	 *
	 * @param startConfigId
	 *            the start config id
	 * @return the start configuration
	 */
	StartConfigBean getStartConfiguration(Long startConfigId);

	/**
	 * Gets the game types.
	 *
	 * @return the game types
	 */
	List<GameTypeBean> getGameTypes();
}
