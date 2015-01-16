package com.ibooking.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibooking.dao.*;
import com.ibooking.po.*;

public class OrderDaoHibernate extends HibernateDaoSupport implements OrderDao {
	@Override
	public Order get(Integer id) {
		return getHibernateTemplate().get(Order.class, id);
	}

	@Override
	public Integer save(Order order) {
		return (Integer) getHibernateTemplate().save(order);
	}

	@Override
	public void update(Order order) {
		getHibernateTemplate().update(order);
	}

	@Override
	public void delete(Order order) {
		getHibernateTemplate().delete(order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAll() {
		return getHibernateTemplate().find("from Order");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findByUserName(String userName) {
		return getHibernateTemplate().find("from Order as u where u.userName = ?", userName);
	}
}
