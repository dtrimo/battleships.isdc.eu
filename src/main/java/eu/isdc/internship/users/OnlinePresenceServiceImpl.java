package eu.isdc.internship.users;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class OnlinePresenceServiceImpl implements OnlinePresenceService{

	private Map<Long,Long> userConnectionCountMap = new ConcurrentHashMap<Long, Long>();

	private Set<String> connectedSessionIds = new HashSet<String>();
	
	public boolean isUserConnected(Long userId) {
		Long connectionCount = userConnectionCountMap.get(userId);
		return connectionCount!=null && connectionCount > 0;
	}

	public boolean notifyUserConnected(Long userId, String sessionId) {
		if (connectedSessionIds.contains(sessionId)){
			return false;
		}
		if (!userConnectionCountMap.containsKey(userId)){
			userConnectionCountMap.put(userId, 1l);
		} else {
			Long connectionCount;
			do {
				connectionCount = userConnectionCountMap.get(userId);				
			} while (!userConnectionCountMap.replace(userId, connectionCount, connectionCount + 1));
		}
		connectedSessionIds.add(sessionId);
		return true;
	}

	public boolean notifyUserDisconnected(Long userId, String sessionId) {
		if (!userConnectionCountMap.containsKey(userId) || !connectedSessionIds.contains(sessionId)){
			return false;
		} else {
			Long connectionCount;
			do {
				connectionCount = userConnectionCountMap.get(userId);				
			} while (!userConnectionCountMap.replace(userId, connectionCount, connectionCount - 1));
			connectedSessionIds.remove(sessionId);
			return true;
		}
	}
	
}
