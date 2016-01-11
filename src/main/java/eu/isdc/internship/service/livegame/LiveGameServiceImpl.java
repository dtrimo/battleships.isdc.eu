package eu.isdc.internship.service.livegame;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.isdc.internship.beans.OccupiedPositionBean;
import eu.isdc.internship.beans.StartConfigBean;
import eu.isdc.internship.persistence.dto.GameDTO;
import eu.isdc.internship.service.GameService;
import eu.isdc.internship.service.livegame.events.CurrentStateTimeoutEvent;
import eu.isdc.internship.service.livegame.events.GameEvent;
import eu.isdc.internship.service.livegame.events.UserFiredEvent;
import eu.isdc.internship.service.livegame.events.UserSubmittedConfigScreenEvent;
import eu.isdc.internship.service.livegame.outbound.OutboundGameEventGateway;
import eu.isdc.internship.service.livegame.timing.GameTimer;

/**
 * The Class LiveGameServiceImpl.
 */
@Service
public class LiveGameServiceImpl implements LiveGameService {

	/** The Constant roundDuration. */
	private static final long roundDuration = 20000l; // 20s

	/** The Constant selectionScreenDuration. */
	private static final long selectionScreenDuration = 60000l; // 60s

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LiveGameServiceImpl.class);

	/** The outbound event gateway. */
	@Autowired
	private OutboundGameEventGateway outboundEventGateway;

	/** The game service. */
	@Autowired
	private GameService gameService;

	/** The game timer. */
	@Autowired
	private GameTimer gameTimer;

	/** The live games. */
	private Map<Long, LiveGameState> liveGames = new HashMap<Long, LiveGameState>();

	/**
	 * {@inheritDoc}
	 */
	public void startGame(Long gameId) {
		if (liveGames.containsKey(gameId)) {
			LOGGER.warn("A live game for the game with id: " + gameId + " is already in progress");
			return;
		}
		GameDTO gameDTO = gameService.getGame(gameId);
		if (gameDTO != null) {
			LiveGameState liveGameState = new LiveGameState(gameDTO);
			liveGameState.setGameState(GameState.CONFIG_SELECTION);
			liveGames.put(gameId, liveGameState);
			gameTimer.startSelectionScreenTimer(gameId, selectionScreenDuration);
		} else {
			throw new IllegalArgumentException("Cannot create live game for game with id: " + gameId);
		}
	}

	/**
	 * Process game event.
	 *
	 * @param event
	 *            the event
	 */
	public void _processGameEvent(CurrentStateTimeoutEvent event) {
		Long gameId = event.getGameId();
		LiveGameState liveGameState = liveGames.get(gameId);
		if (liveGameState == null) {
			LOGGER.debug("Game with id " + gameId + " is not live");
			return;
		}
		if (event.getState() != liveGameState.getGameState()) {
			LOGGER.debug("Game state has changed. CurrentStateTimeoutEvent will be ignored");
		} else {
			if (event.getState() == GameState.CONFIG_SELECTION) {
				// One of the players has not submitted (otherwise the config
				// would have ended already)
				// Players will be notified that the configuration phase has
				// ended
				endConfig(liveGameState);
				// The game will be removed and considered finished
				liveGames.remove(event.getGameId());
				// TODO: Decide winner if applicable
				return;
			}
			if (event.getState() == GameState.ROUND) {
				Integer round = (Integer) event.getInnerState();
				endRound(liveGameState, round);
			}
		}
	}

	/**
	 * Process game event.
	 *
	 * @param event
	 *            the event
	 */
	public void _processGameEvent(UserFiredEvent event) {
		Long gameId = event.getGameId();
		LiveGameState liveGameState = liveGames.get(gameId);
		if (liveGameState == null) {
			LOGGER.debug("Game with id " + gameId + " is not live");
			return;
		}
		Integer currentGameRound = liveGameState.getCurrentRound();
		if (!currentGameRound.equals(event.getRound()) || liveGameState.getGameState() != GameState.ROUND) {
			LOGGER.debug("Invalid time for UserFiredEvent");
			return;
		}

		Long userId = event.getUserId();
		Map<Integer, OccupiedPositionBean> userShots = liveGameState.getShots(userId);
		if (userShots != null) {
			if (!userShots.containsKey(currentGameRound)) {
				userShots.put(currentGameRound, new OccupiedPositionBean(event.getX(), event.getY()));
				outboundEventGateway.notifyFiringResult(liveGameState, userId, currentGameRound);
			} else {
				LOGGER.warn("User with id " + userId + " has already fired in round " + currentGameRound
						+ " of the game with id " + gameId);
				return;
			}
		}

		// if all (both) players have fired on the current round, end it early
		if (liveGameState.allUsersFired(currentGameRound)) {
			endRound(liveGameState, currentGameRound);
		}
	}

	public void _processGameEvent(UserSubmittedConfigScreenEvent event) {
		Long gameId = event.getGameId();
		Long startConfigId = event.getStartConfigId();
		LiveGameState liveGameState = liveGames.get(gameId);
		if (liveGameState == null) {
			LOGGER.error("UserSubmittedConfigScreenEven for game that is not live with id: " + gameId);
			return;
		}
		StartConfigBean startConfig = gameService.getStartConfiguration(startConfigId);
		liveGameState.getStartConfigs().put(startConfig.getGameRole(), startConfig);
		liveGameState.getStartConfigsSubmitted().put(startConfig.getGameRole(), true);
		if (liveGameState.allUsersSubmittedConfig()) {
			endConfig(liveGameState);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void processGameEvent(GameEvent event) {
		if (event instanceof UserSubmittedConfigScreenEvent) {
			_processGameEvent((UserSubmittedConfigScreenEvent) event);
		} else if (event instanceof UserFiredEvent) {
			_processGameEvent((UserFiredEvent) event);
		} else if (event instanceof CurrentStateTimeoutEvent) {
			_processGameEvent((CurrentStateTimeoutEvent) event);
		} else {
			LOGGER.warn("Unsupported game event received" + event);
		}
	}

	/**
	 * Register with game timer.
	 */
	@PostConstruct
	public void registerWithGameTimer() {
		gameTimer.addGameEventConsumer(this);
	}

	/**
	 * End round.
	 *
	 * @param liveGameState
	 *            the live game state
	 * @param round
	 *            the round
	 */
	private void endRound(LiveGameState liveGameState, Integer round) {
		Long gameId = liveGameState.getGameId();
		if (liveGameState.getGameState() == GameState.ROUND && round.equals(liveGameState.getCurrentRound())) {
			liveGameState.setCurrentRound(liveGameState.getCurrentRound() + 1);
			if (liveGameState.getWinner() != null) {
				liveGameState.setGameState(GameState.FINISHED);
			} else {
				gameTimer.startRoundTimer(gameId, liveGameState.getCurrentRound(), roundDuration);
			}
			outboundEventGateway.notifyEndRound(liveGameState);
		} else {
			LOGGER.debug("Round " + round + " has already ended for game with id " + gameId);
		}
	}

	private void endConfig(LiveGameState liveGameState) {
		Long gameId = liveGameState.getGameId();
		if (liveGameState.getGameState() == GameState.CONFIG_SELECTION) {
			liveGameState.setCurrentRound(1);
			outboundEventGateway.notifyEndConfig(liveGameState);
			liveGameState.setGameState(GameState.ROUND);
			gameTimer.startRoundTimer(gameId, liveGameState.getCurrentRound(), roundDuration);
		} else {
			LOGGER.debug("Config period has already ended for game with id " + gameId);
		}
	}

}
