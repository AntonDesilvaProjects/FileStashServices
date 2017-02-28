package com.filestash.user;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.filestash.exception.SessionException;

public class Session {

	private int userId;
	private UUID sessionId;
	private LocalDateTime sessionStartTime;
	private LocalDateTime sessionEndTime;
	
	private final long SESSION_MAX_SIZE = 1000 * 60 * 30; //30 min in millisecs
	
	private Session( int userId )
	{
		this.userId = userId;
		this.sessionId = UUID.randomUUID();
		sessionStartTime = LocalDateTime.now();
		sessionEndTime = sessionStartTime.plusMinutes(30);
	}
	
	public static Session createSession(int userId )
	{
		return new Session( userId );
	}

	public int getUserId() {
		return userId;
	}

	public UUID getSessionId() {
		return sessionId;
	}

	public long getTimeRemaining() {
		
		long timeRemaining = ChronoUnit.MILLIS.between(sessionEndTime, LocalDateTime.now());
		return  timeRemaining <= 0 ? 0 : timeRemaining ;
	}

	public void extendSession() throws SessionException {
		if( this.getTimeRemaining() == 0 )
			throw new SessionException("Session expired!");
		
		//This line contains an ERROR !!!
		this.sessionEndTime.plusSeconds((int)(this.SESSION_MAX_SIZE - this.getTimeRemaining()/ 1000.0) );
	}
	
}
