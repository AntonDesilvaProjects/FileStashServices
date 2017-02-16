package com.filestash.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filestash.dao.ContentDaoImpl;
import com.filestash.dao.LogDaoImpl;
import com.filestash.domain.Content;
import com.filestash.domain.LogItem;

@Service
public class ContentService {
	
	@Autowired
	ContentDaoImpl contentDao;
	@Autowired
	LogDaoImpl logDao;
	
	public ContentService() {}
	
	public List<Content> getDirectoryContents(int directoryId)
	{
		return contentDao.getFolderContentsById(directoryId);
	}
	
	public Content getContentById(int contentId)
	{
		return contentDao.getContentById(contentId);
	}
	
	public long getFileStashSize(int userId)
	{
		return contentDao.getFileStashSize(userId);
	}
	
	public LocalDateTime getLastModifiedDate(int userId)
	{
		return contentDao.getLastModifiedDate(userId);
	}
	
	public List<LogItem> getFileStashLogs( int userId, long from, long to, int pageSize, int page )
	{
		if(pageSize < -1)
			pageSize = -1;
		if(page < 0)
			page = 0;
		
		LocalDateTime fromTime = Instant.ofEpochMilli(from).atZone(ZoneId.systemDefault().systemDefault()).toLocalDateTime();
		LocalDateTime toTime = Instant.ofEpochMilli(to).atZone(ZoneId.systemDefault().systemDefault()).toLocalDateTime(); 
		
		return logDao.getFileStashLogs(userId, fromTime, toTime, pageSize, page);
	}
	public List<LogItem> getContentLogs( int contentId, long from, long to, int pageSize, int page )
	{
		if(pageSize < -1)
			pageSize = -1;
		if(page < 0)
			page = 0;
		
		LocalDateTime fromTime = Instant.ofEpochMilli(from).atZone(ZoneId.systemDefault().systemDefault()).toLocalDateTime();
		LocalDateTime toTime = Instant.ofEpochMilli(to).atZone(ZoneId.systemDefault().systemDefault()).toLocalDateTime(); 
		
		return logDao.getContentLogs(contentId, fromTime, toTime, pageSize, page);
	}
}
