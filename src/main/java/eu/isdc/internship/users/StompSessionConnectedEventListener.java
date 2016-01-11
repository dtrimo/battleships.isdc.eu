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

import eu.isdc.internship.persistence.dto.UserDTO;

@Component
public class StompSessionConnectedEventListener implements ApplicationListener<SessionConnectedEvent> {
	 
    private final Log LOGGER = LogFactory.getLog(StompSessionConnectedEventListener.class);
 
    @Autowired
    private SimpMessagingTemplate template;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OnlinePresenceService onlinePresenceService;
    
    @EventListener
    public void onApplicationEvent(SessionConnectedEvent event) {
        Principal principal = event.getUser();
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        UserDTO user = userService.getUserByName(principal.getName());
        Long userId = Long.valueOf(user.getUserId());
        if (onlinePresenceService.notifyUserConnected(userId, sha.getSessionId())){
        	UserConnectedMessage message = new UserConnectedMessage();
        	message.setUserId(userId);
        	template.convertAndSend("/topic/onlinePresence", message);        	
        }
    }
}