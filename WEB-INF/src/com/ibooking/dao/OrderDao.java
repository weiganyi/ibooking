package com.ibooking.dao;

import java.util.List;

import com.ibooking.po.*;

public interface OrderDao {
	Order get(Integer id);
	
	Integer save(Order order);
	
	void update(Order order);
	
	void delete(Order order);
	
	List<Order> findAll();
	
	List<Order> findByUserName(String userName);
}
