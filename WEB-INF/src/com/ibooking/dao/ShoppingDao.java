package com.ibooking.dao;

import java.util.List;

import com.ibooking.po.*;

public interface ShoppingDao {
	Shopping get(Integer id);
	
	Integer save(Shopping shopping);
	
	void update(Shopping shopping);
	
	void delete(Shopping shopping);
	
	List<Shopping> findAll();

	List<Shopping> findByName(String userName, String menuName);
	
	List<Shopping> findByUserName(String userName);
}
