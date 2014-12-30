package com.ibooking.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibooking.dao.*;
import com.ibooking.po.*;

public class OptionDaoHibernate extends HibernateDaoSupport implements OptionDao {
	private static final String TABLE_NAME="ib_option";

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
	public Option get(Integer id) {
		return getHibernateTemplate().get(Option.class, id);
	}

	@Override
	public Integer save(Option option) {
		return (Integer) getHibernateTemplate().save(option);
	}

	@Override
	public void update(Option option) {
		getHibernateTemplate().update(option);
	}

	@Override
	public void delete(Option option) {
		getHibernateTemplate().delete(option);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Option> findAll() {
		return getHibernateTemplate().find("from Option");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Option> findByName(String name) {
		return getHibernateTemplate().find("from Option as u where u.name = ?", name);
	}
}
