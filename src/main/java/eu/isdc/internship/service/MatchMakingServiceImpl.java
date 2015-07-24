package eu.isdc.internship.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.isdc.internship.beans.GameRequest;
import eu.isdc.internship.beans.GameRequestResponse;
import eu.isdc.internship.beans.GameRole;
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
		final Long gametypeId = gameRequest.getGametypeId();
		List<Long> requestsQueue = pendingGameRequests.get(gametypeId);
		if (requestsQueue == null){
			throw new MatchMakingException("Invalid game type");
		}
		GameUnderConstruction gameUnderConstruction = gamesUnderConstruction.get(gameRequest.getGametypeId());
		int order = gameUnderConstruction.getUsers().size();
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
		for (final GameTypeDTO dto : gameTypeService.getGameTypes()){
			pendingGameRequests.put(dto.getGame_type_id(), new ArrayList<Long>());
			gamesUnderConstruction.put(dto.getGame_type_id(), gameUnderConstructionFactory.newGame(2,dto.getGame_type_id()));
		}		
	}
	
}
