package eu.isdc.internship.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class UserMessagingController {
	
    @Autowired
    private SimpMessagingTemplate template;
    
    @MessageMapping("/sendMessage")
    public void handleUserMessage(UserMessage userMessage){
    	template.convertAndSendToUser(userMessage.getReceiverName(), "/abc", userMessage);
    }
	
}
