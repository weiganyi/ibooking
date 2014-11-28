package com.ibooking.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibooking.dao.MenuDao;
import com.ibooking.po.Menu;

public class MenuDaoHibernate extends HibernateDaoSupport implements MenuDao {

	@Override
	public Menu get(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Menu.class, id);
	}

	@Override
	public Integer save(Menu menu) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(menu);
	}

	@Override
	public void update(Menu menu) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(menu);
	}

	@Override
	public void delete(Menu menu) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(menu);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findAll() {
		// TODO Auto-generated method stub
		return (List<Menu>)getHibernateTemplate().find("from Menu");
	}

}
