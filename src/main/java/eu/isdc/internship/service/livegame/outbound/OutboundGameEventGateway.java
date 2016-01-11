package eu.isdc.internship.service.livegame.outbound;

import eu.isdc.internship.service.livegame.LiveGameState;

public interface OutboundGameEventGateway {

	/**
	 * Notifies both players that a round has ended and what their opponent has moved.
	 * @param liveGameState
	 */
	void notifyEndRound(LiveGameState liveGameState);
	
	/**
	 * Notify a user whether his fire action in the given round resulted in a hit or miss.
	 * The relevant state must already be set in liveGameState.
	 *
	 * @param liveGameState the live game state
	 * @param userId the user id
	 * @param round the round
	 */
	void notifyFiringResult(LiveGameState liveGameState, Long userId, Integer round);
	
	/**
	 * Notify all players in a game that the config period has ended.
	 *
	 * @param liveGameState the live game state
	 */
	void notifyEndConfig(LiveGameState liveGameState);
}
