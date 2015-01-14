package com.ibooking.dao;

import java.util.List;

import com.ibooking.po.*;

public interface UserDao {
	User get(Integer id);
	
	Integer save(User user);
	
	void update(User user);
	
	void delete(User user);
	
	List<User> findAll();

	List<User> findByName(String name);
}
