package eu.isdc.internship.service.livegame.events;

import java.util.Date;

import eu.isdc.internship.service.livegame.GameState;

public class CurrentStateTimeoutEvent implements GameEvent{

	private GameState state;
	private Long gameId;
	private Date stateStart;
	private Date stateEnd;
	private Object innerState;

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public Date getStateStart() {
		return stateStart;
	}

	public void setStateStart(Date stateStart) {
		this.stateStart = stateStart;
	}

	public Date getStateEnd() {
		return stateEnd;
	}

	public void setStateEnd(Date stateEnd) {
		this.stateEnd = stateEnd;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public Object getInnerState() {
		return innerState;
	}

	public void setInnerState(Object innerState) {
		this.innerState = innerState;
	}

}
