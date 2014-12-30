package com.ibooking.dao;

import java.util.List;

import com.ibooking.po.*;
import com.ibooking.util.RedisException;

public interface UserDao {
	User get(Integer id);
	
	Integer save(User user) throws RedisException;
	
	void update(User user);
	
	void delete(User user);
	
	List<User> findAll();

	List<User> findByName(String name);
}
