package eu.isdc.internship.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

import eu.isdc.internship.persistence.dto.GameDTO;

public class GameUnderConstruction {

	private Long gameId;
	private List<Long> users = new ArrayList<Long>();
	private Long gametypeId;
	private Integer numberOfPlayers;
	private GameDTO constructedGame;
	private GameService gameService;
	private CyclicBarrier barrier;
	private boolean canceled;

	public GameUnderConstruction(GameService gameCreationService, Integer numberOfPlayers, Long gametypeId) {
		this.gameService = gameCreationService;
		this.numberOfPlayers = numberOfPlayers;
		this.gametypeId = gametypeId;
		createBarrier();
	}

	private void createBarrier() {
		this.barrier = new CyclicBarrier(numberOfPlayers, new Runnable() {
			public void run() {
				if (!canceled) {
					constructedGame = gameService.createGame(gametypeId, users);
					gameId = constructedGame.getGameId();
				}
			}
		});
	}

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

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

}
