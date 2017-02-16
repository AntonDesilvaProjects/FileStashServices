package com.filestash.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.filestash.domain.User;
import com.filestash.mapper.UserMapper;

@Component
public class UserDaoImpl implements IUserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public User getUserById(int id) {
		String sql = "SELECT * FROM user WHERE user_id = ?";
		User user = (User) jdbcTemplate.queryForObject(sql, new Object[] {id}, new UserMapper());
		return user;
	}	
}
