package com.ibooking.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibooking.dao.MenuDao;
import com.ibooking.po.Menu;

public class MenuDaoHibernate extends HibernateDaoSupport implements MenuDao {
	private static final String TABLE_NAME="ib_menu";

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
	public Menu get(Integer id) {
		return getHibernateTemplate().get(Menu.class, id);
	}

	@Override
	public Integer save(Menu menu) {
		return (Integer) getHibernateTemplate().save(menu);
	}

	@Override
	public void update(Menu menu) {
		getHibernateTemplate().update(menu);
	}

	@Override
	public void delete(Menu menu) {
		getHibernateTemplate().delete(menu);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findAll() {
		return getHibernateTemplate().find("from Menu");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findByPicAddr(String picAddr) {
		return getHibernateTemplate().find("from Menu as u where u.picture = ?", picAddr);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized List<Menu> findByName(String menuName) {
		return getHibernateTemplate().find("from Menu as u where u.name = ?", menuName);
	}
}
