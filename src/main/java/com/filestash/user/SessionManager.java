package com.filestash.user;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.filestash.exception.SessionException;

public class SessionManager {

	private HashMap<UUID, Session> sessionMap;
	
	private SessionManager()
	{
		sessionMap = new HashMap<UUID, Session>();
	}
	
	public static SessionManager createSessionManager()
	{
		return new SessionManager();
	}
	
	public void createSession(int userId)
	{
		Session newSession = Session.createSession(userId);
		sessionMap.put(newSession.getSessionId(), newSession);
	}
	
	public boolean extendSession(UUID sessionId)
	{
		try
		{
			sessionMap.get(sessionId).extendSession();
			return true;
		}
		catch(SessionException sessionExc)
		{
			sessionMap.remove(sessionId);
			sessionExc.printStackTrace();
			return false;
		}
		catch(NullPointerException nullExc)
		{
			nullExc.printStackTrace();
			return false;
		}
	}
	
}
