package com.diary.module.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

	
	public UserDto loginUser(UserDto dto);
	public int insertUser(UserDto dto);
}
