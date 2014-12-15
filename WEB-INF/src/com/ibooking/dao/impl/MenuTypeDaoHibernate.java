package com.ibooking.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibooking.dao.MenuTypeDao;
import com.ibooking.po.MenuType;

public class MenuTypeDaoHibernate extends HibernateDaoSupport implements
		MenuTypeDao {
	private static final String TABLE_NAME="ib_menu_type";

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
	public MenuType get(Integer id) {
		return getHibernateTemplate().get(MenuType.class, id);
	}

	@Override
	public Integer save(MenuType menuType) {
		return (Integer) getHibernateTemplate().save(menuType);
	}

	@Override
	public void update(MenuType menuType) {
		getHibernateTemplate().update(menuType);
	}

	@Override
	public void delete(MenuType menuType) {
		getHibernateTemplate().delete(menuType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuType> findAll() {
		return (List<MenuType>)getHibernateTemplate().find("from MenuType");
	}
}
