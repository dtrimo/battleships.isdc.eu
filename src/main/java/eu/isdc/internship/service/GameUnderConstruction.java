package eu.isdc.internship.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class GameUnderConstruction {

	private static int counter = 0; 
	
	private Integer gameId;
	private List<Integer> users = new ArrayList<Integer>();
	private CyclicBarrier barrier = new CyclicBarrier(2,new Runnable(){
		public void run(){
			gameId = counter++;
		}
	});

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public List<Integer> getUsers() {
		return users;
	}

	public void setUsers(List<Integer> users) {
		this.users = users;
	}

	public CyclicBarrier getBarrier() {
		return barrier;
	}

}
