package com.filestash.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.filestash.services.ContentService;
import com.filestash.domain.*;

/*
 * Need to support
 * 	1) Get information about individual files/folders
 * 	2) Get a directory "level"( all files and folder within that level)
 * 	3) Get general filestash info(size, number of files, etc).
 * 
*/

@RestController
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	ContentService contentService;

	//-------------- Individual File/Folder level handlers ---------------------------
	
	@RequestMapping(value="/{contentId}", method=RequestMethod.GET)
	@CrossOrigin
	public Content getContentInfo(@PathVariable int contentId)
	{
		return contentService.getContentById(contentId);
	}
	
	@RequestMapping(value="/directory/{contentId}", method = RequestMethod.GET)
	@CrossOrigin
	public List<Content> getDirectory(@PathVariable("contentId") int directoryId)
	{
		return contentService.getDirectoryContents(directoryId);
	}
	
	@RequestMapping(value="/logs", method = RequestMethod.GET)
	@CrossOrigin
	public List<LogItem> getContentLogs( @RequestParam("contentId") int contentId, 
										 @RequestParam("from")		long from,
										 @RequestParam("to") 		long to,
										 @RequestParam("pageSize") 	int pageSize,
										 @RequestParam("page") 		int page ) {
	
		return contentService.getContentLogs(contentId, from, to, pageSize, page);
	}
	
	
	//-------------- General FileStash level handlers for a user ----------------------
	
	@RequestMapping(value="/general/size/{userId}", method=RequestMethod.GET)
	@CrossOrigin
	public long getFileStashSize(@PathVariable("userId")int userId)
	{
		return contentService.getFileStashSize(userId);
	}
	
	@RequestMapping(value="/general/lastModified/{userId}", method=RequestMethod.GET)
	@CrossOrigin
	public LocalDateTime getLastModifiedDate(@PathVariable("userId") int userId)
	{
		return contentService.getLastModifiedDate(userId);
	}
	
	@RequestMapping(value="/general/logs", method = RequestMethod.GET)
	@CrossOrigin
	public List<LogItem> getFileStashLogs( @RequestParam("userId") int userId, 
										 @RequestParam("from")		long from,
										 @RequestParam("to") 		long to,
										 @RequestParam("pageSize") 	int pageSize,
										 @RequestParam("page") 		int page ) {
	
		return contentService.getFileStashLogs(userId, from, to, pageSize, page);
	}
}
