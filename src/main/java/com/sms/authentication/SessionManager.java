package com.sms.authentication;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class SessionManager {
    private Logger logger = null;
    private HashMap<String, Integer> sessionIdUserIdMap;
    
    public SessionManager() {
	logger = Logger.getLogger(SessionManager.class);
	sessionIdUserIdMap = new HashMap<String, Integer>();
    }
    
    public void insertSession(String sessionId, Integer userId) {
	logger.debug("User '" + userId + "' logon with session '" + sessionId + "'.");
	
	// Remove old session
	sessionIdUserIdMap.entrySet().removeIf(e -> e.getValue() == userId);

	// Put new session
	sessionIdUserIdMap.put(sessionId, userId);
    }
    
    public void removeSession(String sessionId) {
	logger.debug("Remove session '" + sessionId + "'.");
	sessionIdUserIdMap.remove(sessionId);
    }
    
    public void removeSession(Integer userId) {
    	sessionIdUserIdMap.entrySet().removeIf(e -> e.getValue() == userId);
    }
    
    public Integer getUserIdBySessionId(String sessionId) {
	return sessionIdUserIdMap.getOrDefault(sessionId, null);
    }
    
    public boolean isValidSession(String sessionId) {
	return sessionIdUserIdMap.containsKey(sessionId);
    }
}
