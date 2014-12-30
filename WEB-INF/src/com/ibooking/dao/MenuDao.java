package com.ibooking.dao;

import java.util.List;

import com.ibooking.po.*;

public interface MenuDao {
	Menu get(Integer id);
	
	Integer save(Menu menu);
	
	void update(Menu menu);
	
	void delete(Menu menu);
	
	List<Menu> findAll();
}
