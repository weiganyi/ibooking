package com.ibooking.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibooking.dao.UserDao;
import com.ibooking.po.User;

public class UserDaoHibernate extends HibernateDaoSupport implements UserDao {
	@Override
	public User get(Integer id) {
		return getHibernateTemplate().get(User.class, id);
	}

	@Override
	public Integer save(User user) {
		return (Integer) getHibernateTemplate().save(user);
	}

	@Override
	public void update(User user) {
		getHibernateTemplate().update(user);
	}

	@Override
	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return getHibernateTemplate().find("from User");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByName(String name) {
		return getHibernateTemplate().find("from User as u where u.name = ?", name);
	}
}
