package com.ibooking.dao;

import java.util.List;

import com.ibooking.po.*;

public interface OptionDao {
	Option get(Integer id);
	
	Integer save(Option option);
	
	void update(Option option);
	
	void delete(Option option);
	
	List<Option> findAll();

	List<Option> findByName(String name);
}
