package eu.isdc.internship.service.livegame.outbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import eu.isdc.internship.beans.GameRole;
import eu.isdc.internship.beans.StartConfigBean;
import eu.isdc.internship.service.livegame.GameState;
import eu.isdc.internship.service.livegame.LiveGameState;
import eu.isdc.internship.service.livegame.outbound.messages.EndConfigMessage;
import eu.isdc.internship.service.livegame.outbound.messages.EndRoundMessage;
import eu.isdc.internship.service.livegame.outbound.messages.FiringResultMessage;

@Component
public class OutboundGameEventGatewayImpl implements OutboundGameEventGateway {

	@Autowired
	private SimpMessagingTemplate template;

	public void notifyEndRound(LiveGameState liveGameState) {
		Long gameId = liveGameState.getGameId();
		Integer round = liveGameState.getCurrentRound() - 1;

		StartConfigBean configUser1 = liveGameState.getStartConfigs().get(GameRole.PLAYER_1);
		Long userId1 = configUser1.getUserId();

		StartConfigBean configUser2 = liveGameState.getStartConfigs().get(GameRole.PLAYER_2);
		Long userId2 = configUser2.getUserId();

		EndRoundMessage endRoundMessageUser1 = new EndRoundMessage();
		endRoundMessageUser1.setEnemyShot(liveGameState.getShots(userId2).get(round));
		endRoundMessageUser1.setRound(round);
		endRoundMessageUser1.setGameId(gameId);
		endRoundMessageUser1.setGameEnded(liveGameState.getGameState() == GameState.FINISHED);
		GameRole winner = liveGameState.getWinner();
		if (winner != null) {
			if (liveGameState.getUsers().get(winner).equals(userId1)) {
				endRoundMessageUser1.setWon(true);
			}
		}
		template.convertAndSend("/game/" + gameId + "/" + userId1, endRoundMessageUser1);

		EndRoundMessage endRoundMessageUser2 = new EndRoundMessage();
		endRoundMessageUser2.setEnemyShot(liveGameState.getShots(userId1).get(round));
		endRoundMessageUser2.setRound(round);
		endRoundMessageUser2.setGameId(gameId);
		endRoundMessageUser2.setGameEnded(liveGameState.getGameState() == GameState.FINISHED);
		if (winner != null) {
			if (liveGameState.getUsers().get(winner).equals(userId2)) {
				endRoundMessageUser2.setWon(true);
			}
		}
		template.convertAndSend("/game/" + gameId + "/" + userId2, endRoundMessageUser2);
	}

	public void notifyFiringResult(LiveGameState liveGameState, Long userId, Integer round) {
		Long gameId = liveGameState.getGameId();
		FiringResultMessage message = new FiringResultMessage();
		message.setRound(round);
		message.setUserId(userId);
		message.setGameId(gameId);
		message.setHit(liveGameState.isHit(userId, round));
		template.convertAndSend("/game/" + gameId + "/" + userId, message);
	}

	public void notifyEndConfig(LiveGameState liveGameState) {
		Long gameId = liveGameState.getGameId();
		EndConfigMessage endConfigMessage = new EndConfigMessage(liveGameState.allUsersSubmittedConfig());
		for (final Long userId : liveGameState.getUserIds()) {
			template.convertAndSend("/game/" + gameId + "/" + userId, endConfigMessage);
		}
	}

}
