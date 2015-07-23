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

@Service
public class MatchMakingServiceImpl implements MatchMakingService {

	@Autowired
	private GameTypeService gameTypeService;
	
	private Map<Integer, List<Integer>> pendingGameRequests =  new HashMap<Integer, List<Integer>>();
	private Map<Integer, GameUnderConstruction> gamesUnderConstruction = new HashMap<Integer, GameUnderConstruction>();
	
	public GameRequestResponse requestGame(GameRequest gameRequest) throws MatchMakingException {
		List<Integer> requestsQueue = pendingGameRequests.get(gameRequest.getGametypeId());
		if (requestsQueue == null){
			throw new MatchMakingException("Invalid game type");
		}
		GameUnderConstruction gameUnderConstruction = gamesUnderConstruction.get(gameRequest.getGametypeId());
		int order = gameUnderConstruction.getUsers().size()+1;
		gameUnderConstruction.getUsers().add(gameRequest.getUserId());
		
		try {
			gameUnderConstruction.getBarrier().await();
		} catch (InterruptedException e) {
			throw new MatchMakingException(e);
		} catch (BrokenBarrierException e) {
			throw new MatchMakingException(e);
		}
		gamesUnderConstruction.put(gameRequest.getGametypeId(),new GameUnderConstruction());
		
		GameRequestResponse response = new GameRequestResponse();
		response.setGameId(gameUnderConstruction.getGameId());
		response.setGameRole(order == 1 ? GameRole.PLAYER_1 : GameRole.PLAYER_2);
		return response;
	}

	@PostConstruct
	public void initializePendingGameRequestsMap(){
		for (final GameTypeDTO dto : gameTypeService.getGameTypes()){
			pendingGameRequests.put(dto.getGame_type_id().intValue(), new ArrayList<Integer>());
			gamesUnderConstruction.put(dto.getGame_type_id().intValue(),new GameUnderConstruction());
		}		
	}
	
}
