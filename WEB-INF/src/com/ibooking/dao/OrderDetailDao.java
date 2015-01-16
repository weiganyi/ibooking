package com.ibooking.dao;

import java.util.List;

import com.ibooking.po.*;

public interface OrderDetailDao {
	OrderDetail get(Integer id);
	
	Integer save(OrderDetail orderDetail);
	
	void update(OrderDetail orderDetail);
	
	void delete(OrderDetail orderDetail);
	
	List<OrderDetail> findAll();
	
	List<OrderDetail> findByOrderId(int orderId);
}
