package com.filestash.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.filestash.domain.LogItem;

public interface ILogDao {
	//Get all logs for a user
	public List<LogItem> getFileStashLogs( int userId, LocalDateTime from, LocalDateTime to, int pageSize, int page );
	//Get a log for an item
	public List<LogItem> getContentLogs( int contentId, LocalDateTime from, LocalDateTime to,  int pageSize, int page);
}
