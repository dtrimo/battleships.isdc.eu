package eu.isdc.internship.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.isdc.internship.db.dto.GameDTO;

@Service
public class GameUnderConstructionFactory {
	
	@Autowired
	private GameCreationService gameCreationService;
	
	public GameUnderConstruction newGame(Integer numberOfPlayers, Long gametypeId) {
		return new GameUnderConstruction(gameCreationService, numberOfPlayers, gametypeId);
	}

	public static class GameUnderConstruction {

		private Long gameId;
		private List<Long> users = new ArrayList<Long>();
		private Long gametypeId;
		private Integer numberOfPlayers;
		private GameDTO constructedGame;
		private GameCreationService gameCreationService;

		private GameUnderConstruction(GameCreationService gameCreationService, Integer numberOfPlayers, Long gametypeId) {
			this.gameCreationService = gameCreationService;
			this.numberOfPlayers = numberOfPlayers;
			this.gametypeId = gametypeId;
			createBarrier();
		}

		private void createBarrier(){
			this.barrier = new CyclicBarrier(numberOfPlayers, new Runnable(){
				public void run(){
					constructedGame = gameCreationService.createGame(gametypeId, users);
					gameId = constructedGame.getGame_id();
				}
			});
		}
		
		
		private CyclicBarrier barrier;

		public Long getGameId() {
			return gameId;
		}

		public void setGameId(Long gameId) {
			this.gameId = gameId;
		}

		public List<Long> getUsers() {
			return users;
		}

		public void setUsers(List<Long> users) {
			this.users = users;
		}

		public CyclicBarrier getBarrier() {
			return barrier;
		}
		
		public Long getGametypeId() {
			return gametypeId;
		}

		public GameDTO getConstructedGame() {
			return constructedGame;
		}

	}

}
