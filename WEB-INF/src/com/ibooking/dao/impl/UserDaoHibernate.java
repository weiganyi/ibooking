package com.ibooking.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibooking.dao.UserDao;
import com.ibooking.po.User;

public class UserDaoHibernate extends HibernateDaoSupport implements UserDao {
	private static final String TABLE_NAME="ib_user";

	@SuppressWarnings("unchecked")
	public List<BigInteger> getAutoIncrement() {
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();

		String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME=:table"; 
		List<BigInteger> lst = sess.createSQLQuery(sql).setString("table", TABLE_NAME).list();

		sess.close();
		sf.close();
		return lst;
	}
	
	@Override
	public User get(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(User.class, id);
	}

	@Override
	public Integer save(User user) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from User");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByName(String name) {
		return getHibernateTemplate().find("from User as u where u.name = ?", name);
	}
}
