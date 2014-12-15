package com.ibooking.dao.impl;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.ibooking.dao.*;
import com.ibooking.po.*;

public class MenuTypeDaoRedis implements MenuTypeDao {
	private MenuTypeDao menuTypeDaoHbm;
	
	private Jedis jedis = null;
	
	public MenuTypeDaoRedis() {
		//create the redis connect
		jedis = new Jedis("127.0.0.1", 6379);
	}

	public void init() {
		//read and set the auto_increment value from mysql
		List<BigInteger> lstAutoInc = ((MenuTypeDaoHibernate)menuTypeDaoHbm).getAutoIncrement();
		if (lstAutoInc != null && lstAutoInc.size() != 0) {
			Integer autoInc = lstAutoInc.get(0).intValue();
			autoInc--;
			jedis.set("ib_menu_type:auto_increment", autoInc.toString());
		}else {
			System.out.println("MenuTypeDaoRedis.init() fail to read auto_increment from mysql");
		}
		
		//read all record from mysql
		List<MenuType> lstMenuType = menuTypeDaoHbm.findAll();
		for (MenuType menuType : lstMenuType) {
			jedis.set("ib_menu_type:" + menuType.getName() + ":id", menuType.getId().toString());
			
			jedis.set("ib_menu_type:" + menuType.getId().toString() + ":menu_type_name", menuType.getName());
		}
	}

	public MenuTypeDao getMenuTypeDaoHbm() {
		return menuTypeDaoHbm;
	}

	public void setMenuTypeDaoHbm(MenuTypeDao menuTypeDaoHbm) {
		this.menuTypeDaoHbm = menuTypeDaoHbm;
	}
	
	public Integer getNextId() {
		return jedis.incr("ib_menu_type:auto_increment").intValue();
	}

	@Override
	public MenuType get(Integer id) {
		return null;
	}

	@Override
	public Integer save(MenuType menuType) {
		return 0;
	}

	@Override
	public void update(MenuType menuType) {
	}

	@Override
	public void delete(MenuType menuType) {
	}

	@Override
	public List<MenuType> findAll() {
		LinkedList<MenuType> lstMenuType = new LinkedList<MenuType>();
		Set<String> setId = jedis.keys("ib_menu_type:*:id");
		
		//iterator the keys
		for (String key : setId) {
			String id = jedis.get(key);
			String name = jedis.get("ib_menu_type:" + id + ":menu_type_name");
			
			MenuType menuType = new MenuType();
			menuType.setId(Integer.valueOf(id));
			menuType.setName(name);
			
			lstMenuType.add(menuType);
		}

		return lstMenuType;
	}
}