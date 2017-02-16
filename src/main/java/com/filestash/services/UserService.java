package com.filestash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filestash.dao.UserDaoImpl;
import com.filestash.domain.User;

@Service
public class UserService {

	@Autowired
	UserDaoImpl userDao;
	
	public User getUserById(int id)
	{
		return userDao.getUserById(id);
	}
}
