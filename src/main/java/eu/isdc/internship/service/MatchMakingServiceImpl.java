package eu.isdc.internship.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.isdc.internship.beans.GameRequest;
import eu.isdc.internship.beans.GameRequestResponse;
import eu.isdc.internship.beans.GameRole;
import eu.isdc.internship.beans.GameTypeBean;
import eu.isdc.internship.db.dto.GameTypeDTO;
import eu.isdc.internship.exception.MatchMakingException;
import eu.isdc.internship.service.GameUnderConstructionFactory.GameUnderConstruction;

@Service
public class MatchMakingServiceImpl implements MatchMakingService {

	@Autowired
	private GameTypeService gameTypeService;
	
	@Autowired
	private GameUnderConstructionFactory gameUnderConstructionFactory;
	
	private Map<Long, List<Long>> pendingGameRequests =  new HashMap<Long, List<Long>>();
	private Map<Long, GameUnderConstruction> gamesUnderConstruction = new HashMap<Long, GameUnderConstruction>();
	
	public GameRequestResponse requestGame(GameRequest gameRequest) throws MatchMakingException {
		GameUnderConstruction gameUnderConstruction = gamesUnderConstruction.get(gameRequest.getGametypeId());

		final Long gametypeId = gameRequest.getGametypeId();
		int order = gameUnderConstruction.getUsers().size();
		for (Map.Entry<Long, GameUnderConstruction> entry : gamesUnderConstruction.entrySet()) {
		    for (Long user: ((GameUnderConstruction)(entry.getValue())).getUsers())
				if(user.equals(gameRequest.getUserId())){
					throw new MatchMakingException("You already pressed the play button!");
				}
	    }

		gameUnderConstruction.getUsers().add(gameRequest.getUserId());
		
		try {
			gameUnderConstruction.getBarrier().await();
		} catch (InterruptedException e) {
			throw new MatchMakingException(e);
		} catch (BrokenBarrierException e) {
			throw new MatchMakingException(e);
		}
		gamesUnderConstruction.put(gametypeId, gameUnderConstructionFactory.newGame(2,gametypeId));
		
		GameRequestResponse response = new GameRequestResponse();
		response.setGameId(gameUnderConstruction.getGameId());
		response.setGameRole(order == 0 ? GameRole.PLAYER_1 : GameRole.PLAYER_2);
		response.setStartConfigId(gameUnderConstruction.getConstructedGame().getStartConfigs().get(order).getStartConfig_id());
		return response;
	}

	@PostConstruct
	public void initializePendingGameRequestsMap(){
		for (final GameTypeBean gameType : gameTypeService.getGameTypes()){
			pendingGameRequests.put(gameType.getId(), new ArrayList<Long>());
			gamesUnderConstruction.put(gameType.getId(), gameUnderConstructionFactory.newGame(2,gameType.getId()));
		}		
	}
	
}
