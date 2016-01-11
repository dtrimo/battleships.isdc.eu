package eu.isdc.internship.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class AppWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "/user", "/game");
		config.setApplicationDestinationPrefixes("/messaging", "/gameEvents");
	}
	
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/onlinePresence").withSockJS().setHeartbeatTime(2000);
		registry.addEndpoint("/gameLifeCycle").withSockJS();
	}
} 
