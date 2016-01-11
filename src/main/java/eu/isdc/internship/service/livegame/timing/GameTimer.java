package eu.isdc.internship.service.livegame.timing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

import eu.isdc.internship.service.livegame.GameEventConsumer;
import eu.isdc.internship.service.livegame.GameState;
import eu.isdc.internship.service.livegame.events.CurrentStateTimeoutEvent;
import eu.isdc.internship.service.livegame.events.GameEvent;

@Service
public class GameTimer {

	private List<GameEventConsumer> consumers = new ArrayList<GameEventConsumer>();
	private Timer timer = new Timer();
	
	public void startSelectionScreenTimer(Long gameId, long duration){
		final CurrentStateTimeoutEvent timeoutEvent = new CurrentStateTimeoutEvent();
		timeoutEvent.setGameId(gameId);
		timeoutEvent.setState(GameState.CONFIG_SELECTION);
		timeoutEvent.setStateStart(new Date());
		timeoutEvent.setStateEnd(new Date(new Date().getTime()+duration));
		scheduleEvent(timeoutEvent, duration);
	}
	
	public void startRoundTimer(Long gameId, int round, long duration){
		final CurrentStateTimeoutEvent timeoutEvent = new CurrentStateTimeoutEvent();
		timeoutEvent.setGameId(gameId);
		timeoutEvent.setState(GameState.ROUND);
		timeoutEvent.setStateStart(new Date());
		timeoutEvent.setStateEnd(new Date(new Date().getTime()+duration));
		timeoutEvent.setInnerState(Integer.valueOf(round));
		scheduleEvent(timeoutEvent, duration);
	}
	
	public void addGameEventConsumer(GameEventConsumer consumer){
		if (!consumers.contains(consumer)){
			consumers.add(consumer);			
		}
	}
	
	public void removeGameEventConsumer(GameEventConsumer consumer){
		consumers.remove(consumer);
	}
	
	private void scheduleEvent(final GameEvent event, final long delay){		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				for (GameEventConsumer consumer : consumers){
					consumer.processGameEvent(event);
				}
			}
		}, delay);
	}
	
}
