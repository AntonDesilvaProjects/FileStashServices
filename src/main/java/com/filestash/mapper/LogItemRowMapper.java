package com.filestash.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.filestash.domain.LogItem;

public class LogItemRowMapper implements RowMapper<LogItem> {

	@Override
	public LogItem mapRow(ResultSet row, int rowNum) throws SQLException {
		LogItem logItem = new LogItem();
		logItem.setLogId(row.getInt("log_id"));
		logItem.setUserId(row.getInt("user_id"));
		logItem.setContentId(row.getInt("content_id"));
		logItem.setLogTime(row.getTimestamp("log_time").toLocalDateTime());
		logItem.setLogAction(row.getString("log_action"));
		return logItem;
	}

}
