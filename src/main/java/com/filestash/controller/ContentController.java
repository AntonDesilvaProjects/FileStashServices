package com.filestash.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.filestash.services.ContentService;
import com.filestash.domain.*;
import com.filestash.dto.GeneralFileStashInfoDto;
import com.filestash.http.SimpleHttpResponse;

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
	
	@RequestMapping(value="/upload", method=RequestMethod.POST )
	@CrossOrigin
	public SimpleHttpResponse uploadFile(@RequestParam("uploadItem") MultipartFile fileUpload )
	{
		System.out.println(fileUpload.getOriginalFilename());
		try {
			byte[] fileBytes = fileUpload.getBytes();
			Files.write(Paths.get("C:\\Users\\Anton\\Desktop\\" + fileUpload.getOriginalFilename()), fileBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return new SimpleHttpResponse(true/*, fileUpload.getOriginalFilename() + " was successfuly uploaded !"*/);
	}
	//-------------- General FileStash level handlers for a user ----------------------
	/*
	 * @userId - the userId is the owner of the FileStash
	 * @from - start date; -1 is the default value
	 * @to - end date; -1 is the default value
	 * @pageSize - pagination is activated if @pageSize different from default value
	 * -1; Returns the specified amount of records at a time. -1 value returns everything
	 * @page - which page of records to retrieve. defaults to 0(the first page). 
	 * 	Only works if @pageSize is NOT -1. 
	 */
	@RequestMapping(value="/general", method = RequestMethod.GET)
	@CrossOrigin
	public GeneralFileStashInfoDto getFileStashInfo( 
										 @RequestParam("userId") int userId, 
										 @RequestParam("from")		long from,
										 @RequestParam("to") 		long to,
										 @RequestParam("pageSize") 	int pageSize,
										 @RequestParam("page") 		int page ) 
	{
		return contentService.getFileStashInfo(userId, from, to, pageSize, page);
	}
	
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
	public List<LogItem> getFileStashLogs( 
										 @RequestParam("userId") int userId, 
										 @RequestParam("from")		long from,
										 @RequestParam("to") 		long to,
										 @RequestParam("pageSize") 	int pageSize,
										 @RequestParam("page") 		int page ) {
	
		return contentService.getFileStashLogs(userId, from, to, pageSize, page);
	}
}
