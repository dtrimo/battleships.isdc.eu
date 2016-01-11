package eu.isdc.internship.service.livegame;

import eu.isdc.internship.service.livegame.events.GameEvent;

public interface GameEventConsumer {

	//security should be treated at a higher level
	void processGameEvent(GameEvent event);
}
