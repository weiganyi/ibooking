package com.ibooking.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibooking.dao.*;
import com.ibooking.po.*;

public class OrderDetailDaoHibernate extends HibernateDaoSupport implements OrderDetailDao {
	@Override
	public OrderDetail get(Integer id) {
		return getHibernateTemplate().get(OrderDetail.class, id);
	}

	@Override
	public Integer save(OrderDetail orderDetail) {
		return (Integer) getHibernateTemplate().save(orderDetail);
	}

	@Override
	public void update(OrderDetail orderDetail) {
		getHibernateTemplate().update(orderDetail);
	}

	@Override
	public void delete(OrderDetail orderDetail) {
		getHibernateTemplate().delete(orderDetail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetail> findAll() {
		return getHibernateTemplate().find("from OrderDetail");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetail> findByOrderId(int orderId) {
		return getHibernateTemplate().find("from OrderDetail as u where u.orderId = ?", orderId);
	}
}
