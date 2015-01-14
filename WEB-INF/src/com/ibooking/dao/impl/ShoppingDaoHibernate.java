package com.ibooking.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibooking.dao.*;
import com.ibooking.po.*;

public class ShoppingDaoHibernate extends HibernateDaoSupport implements ShoppingDao {
	@Override
	public Shopping get(Integer id) {
		return getHibernateTemplate().get(Shopping.class, id);
	}

	@Override
	public Integer save(Shopping shopping) {
		return (Integer) getHibernateTemplate().save(shopping);
	}

	@Override
	public void update(Shopping shopping) {
		getHibernateTemplate().update(shopping);
	}

	@Override
	public void delete(Shopping shopping) {
		getHibernateTemplate().delete(shopping);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Shopping> findAll() {
		return getHibernateTemplate().find("from Shopping");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Shopping> findByName(String userName, String menuName) {
		return getHibernateTemplate().find("from Shopping as u where u.userName = ? and u.menuName = ?", userName, menuName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Shopping> findByUserName(String userName) {
		return getHibernateTemplate().find("from Shopping as u where u.userName = ?", userName);
	}
}
