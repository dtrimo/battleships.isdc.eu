package eu.isdc.internship.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import eu.isdc.internship.service.livegame.LiveGameService;
import eu.isdc.internship.service.livegame.events.UserFiredEvent;

@Controller
public class LiveGameController {

	@Autowired
	private LiveGameService liveGameService;

	@MessageMapping("/userFired")
	public void handleUserMessage(@Payload UserFiredEvent userFiredEvent) {
		liveGameService.processGameEvent(userFiredEvent);
	}

}
