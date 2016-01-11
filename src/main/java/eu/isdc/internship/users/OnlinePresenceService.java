package eu.isdc.internship.users;

public interface OnlinePresenceService {

	boolean isUserConnected(Long userId);
	
	boolean notifyUserConnected(Long userId, String sessionId);
	
	boolean notifyUserDisconnected(Long userId, String sessionId);
	
}
