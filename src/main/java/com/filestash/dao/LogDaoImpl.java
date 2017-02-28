package com.filestash.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.filestash.domain.LogItem;
import com.filestash.mapper.LogItemRowMapper;

@Component
public class LogDaoImpl implements ILogDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.filestash.dao.IContentDao#getLogs(int, java.time.LocalDateTime, java.time.LocalDateTime, int, int)
	 * If 'from' is null, return all the logs from the beginning to the 'to' time
	 * If 'to' is null, return all the logs from 'from' time until now
	 * 'pageSize' determines how many records to return
	 * 'page' determines which page
	 */
	public List<LogItem> getFileStashLogs(int userId, LocalDateTime from, LocalDateTime to, int pageSize, int page) {
		
		List<LogItem> logs = null;
		String sql = null;
		String paginationSql = " LIMIT ? OFFSET ?";
		Timestamp fromTstmp, toTstmp;
		Object[] parameters = new Object[] {};
		
		if( from == null && to != null )
		{
			//Get everything from the beginning till 'to'
			toTstmp = Timestamp.valueOf(to);
			sql = "SELECT log.*, content.content_name FROM log INNER JOIN content ON log.content_id = content.content_id WHERE log.user_id = ? AND log.log_time <= ? ORDER BY(log.log_time)";
			if( pageSize != -1) //Append pagination SQL only if page size is defined; else return everything
			{
				sql += paginationSql;
				if( page == -1 ) //This case doesn't have to be used; if caller doesn't know page, should use 0
					page = 0;
				parameters = new Object[] { userId, toTstmp, pageSize, pageSize * page };
			}
			else
				parameters = new Object[] { userId, toTstmp };
		}
		else if ( from != null && to == null )
		{
			//Get everything from the 'from' till now
			fromTstmp = Timestamp.valueOf(from);
			sql = "SELECT log.*, content.content_name FROM log INNER JOIN content ON log.content_id = content.content_id WHERE log.user_id = ? AND log.log_time >= ? ORDER BY(log.log_time)";
			if( pageSize != -1) //Append pagination SQL only if page size is defined; else return everything
			{
				sql += paginationSql;
				if( page == -1 ) //This case doesn't have to be used; if caller doesn't know page, should use 0
					page = 0;
				parameters = new Object[] { userId, fromTstmp, pageSize, pageSize * page };
			}
			else
				parameters = new Object[] { userId, fromTstmp };
		}
		else if ( from == null && to == null )
		{
			sql = "SELECT log.*, content.content_name FROM log INNER JOIN content ON log.content_id = content.content_id WHERE log.user_id = ? ORDER BY(log.log_time)";
			if( pageSize != -1 )
			{
				sql += paginationSql;
				if( page == -1 ) //This case doesn't have to be used; if caller doesn't know page, should use 0
					page = 0;
				parameters = new Object[] { userId, pageSize, pageSize * page };
			}
			else
				parameters = new Object[] { userId };	
		}
		else
		{
			fromTstmp = Timestamp.valueOf(from);
			toTstmp = Timestamp.valueOf(to);
			sql = "SELECT log.*, content.content_name FROM log INNER JOIN content ON log.content_id = content.content_id WHERE log.user_id = ? AND log.log_time >= ? AND log.log_time <= ? ORDER BY(log.log_time)";
			if( pageSize != -1) //Append pagination SQL only if page size is defined; else return everything
			{
				sql += paginationSql;
				if( page == -1 ) //This case doesn't have to be used; if caller doesn't know page, should use 0
					page = 0;
				parameters = new Object[] { userId, fromTstmp,  toTstmp, pageSize, pageSize * page };
			}
			else
				parameters = new Object[] { userId, fromTstmp,  toTstmp };
		}
		
		//Get the logs based on above set conditions
		logs = jdbcTemplate.query(sql, parameters , new LogItemRowMapper());
		
		return logs;
	}

	@Override
	public List<LogItem> getContentLogs(int contentId, LocalDateTime from, LocalDateTime to,  int pageSize, int page) {
		List<LogItem> logs = null;
		String sql = null;
		String paginationSql = " LIMIT ? OFFSET ?";
		Timestamp fromTstmp, toTstmp;
		Object[] parameters = new Object[] {};
		
		if( from == null && to != null )
		{
			//Get everything from the beginning till 'to'
			toTstmp = Timestamp.valueOf(to);
			sql = "SELECT log.*, content.content_name FROM log INNER JOIN content ON log.content_id = content.content_id WHERE log.content_id = ? AND log.log_time <= ? ORDER BY(log.log_time)";
			if( pageSize != -1) //Append pagination SQL only if page size is defined; else return everything
			{
				sql += paginationSql;
				if( page == -1 ) //This case doesn't have to be used; if caller doesn't know page, should use 0
					page = 0;
				parameters = new Object[] { contentId, toTstmp, pageSize, pageSize * page };
			}
			else
				parameters = new Object[] { contentId, toTstmp };
		}
		else if ( from != null && to == null )
		{
			//Get everything from the 'from' till now
			fromTstmp = Timestamp.valueOf(from);
			sql = "SELECT log.*, content.content_name FROM log INNER JOIN content ON log.content_id = content.content_id WHERE log.content_id = ? AND log.log_time >= ? ORDER BY(log.log_time)";
			if( pageSize != -1) //Append pagination SQL only if page size is defined; else return everything
			{
				sql += paginationSql;
				if( page == -1 ) //This case doesn't have to be used; if caller doesn't know page, should use 0
					page = 0;
				parameters = new Object[] { contentId, fromTstmp, pageSize, pageSize * page };
			}
			else
				parameters = new Object[] { contentId, fromTstmp };
		}
		else if ( from == null && to == null )
		{
			sql = "SELECT log.*, content.content_name FROM log INNER JOIN content ON log.content_id = content.content_id WHERE log.user_id = ? ORDER BY(log.log_time)";
			if( pageSize != -1 )
			{
				sql += paginationSql;
				if( page == -1 ) //This case doesn't have to be used; if caller doesn't know page, should use 0
					page = 0;
				parameters = new Object[] { contentId, pageSize, pageSize * page };
			}
			else
				parameters = new Object[] { contentId };	
		}
		else
		{
			fromTstmp = Timestamp.valueOf(from);
			toTstmp = Timestamp.valueOf(to);
			sql = "SELECT log.*, content.content_name FROM log INNER JOIN content ON log.content_id = content.content_id WHERE log.content_id = ? AND log.log_time >= ? AND log.log_time <= ? ORDER BY(log.log_time)";
			if( pageSize != -1) //Append pagination SQL only if page size is defined; else return everything
			{
				sql += paginationSql;
				if( page == -1 ) //This case doesn't have to be used; if caller doesn't know page, should use 0
					page = 0;
				parameters = new Object[] { contentId, fromTstmp,  toTstmp, pageSize, pageSize * page };
			}
			else
				parameters = new Object[] { contentId, fromTstmp,  toTstmp };
		}
		
		//Get the logs based on above set conditions
		logs = jdbcTemplate.query(sql, parameters , new LogItemRowMapper());
		
		return logs;
	}


}
