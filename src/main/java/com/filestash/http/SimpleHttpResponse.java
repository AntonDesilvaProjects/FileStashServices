package com.filestash.http;

import org.springframework.stereotype.Component;

@Component
public class SimpleHttpResponse {
	
	private boolean success;
	//private String message;
	
	public SimpleHttpResponse() {};
	public SimpleHttpResponse( boolean success)
	{
		this.success = success;
		//this.message = message;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/*public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}*/
	
	
}
