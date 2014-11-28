package com.ibooking.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibooking.dao.MenuTypeDao;
import com.ibooking.po.MenuType;

public class MenuTypeDaoHibernate extends HibernateDaoSupport implements
		MenuTypeDao {

	@Override
	public MenuType get(Integer id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(MenuType.class, id);
	}

	@Override
	public Integer save(MenuType menuType) {
		// TODO Auto-generated method stub
		return (Integer) getHibernateTemplate().save(menuType);
	}

	@Override
	public void update(MenuType menuType) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(menuType);
	}

	@Override
	public void delete(MenuType menuType) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(menuType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuType> findAll() {
		// TODO Auto-generated method stub
		return (List<MenuType>)getHibernateTemplate().find("from MenuType");
	}

}
