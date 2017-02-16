package com.filestash.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.filestash.domain.Content;
import com.filestash.mapper.ContentMapper;

/*
 * MySql implementation of IConetentDAO
 */

@Component
public class ContentDaoImpl implements IContentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Content getContentById(int id) {
		
		String sql = "SELECT * FROM content WHERE content_id = ?";
		Content content = jdbcTemplate.queryForObject(sql, new Object[] { id }, new ContentMapper());
		return content;
	}

	@Override
	public List<Content> getFolderContentsById(int folderId) {
		
		List<Content> result = null;
		String folderItemsRaw = null;
		String sql = "SELECT dir_items FROM directory_struc WHERE content_id = ?";
		folderItemsRaw = jdbcTemplate.queryForObject(sql, new Object[] { folderId }, String.class);
		
		String[] folderItems = folderItemsRaw.split(",");
		
		if( folderItems.length > 0 )
		{
			StringBuilder strBldr = new StringBuilder();
			//content_id, content_name, content_type, content_image
			strBldr.append("SELECT * FROM content " +
							"WHERE content_id = ? ");
			for(int i = 1; i < folderItems.length; i++ )
			{
				strBldr.append("OR content_id = ? ");
			}
			result = jdbcTemplate.query( strBldr.toString(), folderItems, new ContentMapper());
			return result;
		}
		else
			return result;
	}
	
	@Override
	public long getFileStashSize(int userId) {
		
		String sql = "SELECT SUM(content_size) FROM content WHERE user_id = ?";
		Long sum = jdbcTemplate.queryForObject( sql, new Object[] { userId }, Long.class);
		return sum.longValue();
	}

	@Override
	public LocalDateTime getLastModifiedDate( int userId ) {
		
		String sql = "SELECT MAX(log_time) FROM log WHERE user_id = ?";
		Timestamp maxTstmp = jdbcTemplate.queryForObject(sql, new Object[] { userId }, Timestamp.class);
		return maxTstmp.toLocalDateTime();
	}
}
