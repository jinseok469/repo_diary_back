package com.diary.module.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	public UserDto loginUser(UserDto dto) {
		
		return dao.loginUser(dto);
	}
	
	public int insertUser(UserDto dto) {
		return dao.insertUser(dto);
	}

}
