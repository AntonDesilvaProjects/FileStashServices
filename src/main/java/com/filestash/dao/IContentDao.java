package com.filestash.dao;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.filestash.domain.Content;
import com.filestash.domain.LogItem;

public interface IContentDao {

	//Individual item info
	public Content getContentById( int id );
	public List<Content> getFolderContentsById( int folderId );
	
	//General FileStash
	public long getFileStashSize(int userId);
	public LocalDateTime getLastModifiedDate( int userId);
}
