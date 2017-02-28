package com.filestash.exception;

import org.springframework.stereotype.Component;

public class SessionException extends Exception {

	public SessionException()
	{
		super("Session Error Occured !");
	}
	
	public SessionException(String message)
	{
		super(message);
	}
}
