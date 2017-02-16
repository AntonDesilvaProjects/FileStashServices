package com.filestash.domain;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class LogItem {
	
	private int logId;
	private int contentId;
	private int userId;
	private LocalDateTime logTime;
	private String logAction;

	public LogItem() {}
	
	public LogItem(int logId, int contentId, int userId, LocalDateTime logTime, String logAction) {
		super();
		this.logId = logId;
		this.contentId = contentId;
		this.userId = userId;
		this.logTime = logTime;
		this.logAction = logAction;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getLogTime() {
		return logTime;
	}

	public void setLogTime(LocalDateTime logTime) {
		this.logTime = logTime;
	}

	public String getLogAction() {
		return logAction;
	}

	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}

}
