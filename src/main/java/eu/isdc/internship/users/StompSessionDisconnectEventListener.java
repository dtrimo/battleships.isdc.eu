package eu.isdc.internship.users;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import eu.isdc.internship.persistence.dto.UserDTO;

@Component
public class StompSessionDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {
	 
    private final Log LOGGER = LogFactory.getLog(StompSessionDisconnectEventListener.class);
 
    @Autowired
    private SimpMessagingTemplate template;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OnlinePresenceService onlinePresenceService;
    
    @EventListener
    public void onApplicationEvent(SessionDisconnectEvent event) {
    	StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        Principal principal = sha.getUser();
        UserDTO user = userService.getUserByName(principal.getName());
        Long userId = Long.valueOf(user.getUserId());
        if (onlinePresenceService.notifyUserDisconnected(userId, event.getSessionId())){
        	//we only broadcast the disconnect event if there are no more session for 
        	//the user who just disconnected
        	if (!onlinePresenceService.isUserConnected(userId)){
        		UserDisconnectedMessage message = new UserDisconnectedMessage();
        		message.setUserId(userId);
        		template.convertAndSend("/topic/onlinePresence", message);
        	}        	
        }
    }
}