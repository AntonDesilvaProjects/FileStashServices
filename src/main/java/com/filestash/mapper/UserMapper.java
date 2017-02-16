package com.filestash.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.filestash.domain.User;

@Component
public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet result, int rowNum) throws SQLException {
		User user = new User();
		user.setId(result.getInt("user_id"));
		user.setFirstName(result.getString("first_name"));
		user.setLastName(result.getString("last_name"));
		user.setEmail(result.getString("email"));
		return user;
	}

}
