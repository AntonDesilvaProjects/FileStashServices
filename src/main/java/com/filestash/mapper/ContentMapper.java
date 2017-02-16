package com.filestash.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.filestash.domain.Content;

public class ContentMapper implements RowMapper<Content> {

	@Override
	public Content mapRow(ResultSet result, int rowNum) throws SQLException {
		Content content = new Content();
		content.setId(result.getInt("content_id"));
		content.setOwner(result.getInt("user_id"));
		content.setName(result.getString("content_name"));
		content.setAuthor(result.getString("content_author"));
		content.setPath(result.getString("content_path"));
		content.setType(result.getString("content_type"));
		content.setSize(result.getLong("content_size"));
		content.setUploadTime(result.getTimestamp("content_upload_time").toLocalDateTime());
		content.setLastModified(result.getTimestamp("content_last_mod").toLocalDateTime());
		content.setImage(result.getString("content_image"));
		return content;
	}

}
