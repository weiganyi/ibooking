package com.ibooking.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
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
	
	public synchronized Integer getNextId() {
		return jedis.incr("ib_menu_type:auto_increment").intValue();
	}

	@Override
	public synchronized MenuType get(Integer id) {
		Set<String> setId = jedis.keys("ib_menu_type:*:id");
		
		//iterator the keys
		for (String key : setId) {
			String id2 = jedis.get(key);
			if (Integer.valueOf(id2) == id) {
				String name = jedis.get("ib_menu_type:" + id2 + ":menu_type_name");
				
				MenuType menuType = new MenuType();
				menuType.setId(Integer.valueOf(id));
				menuType.setName(name);
				
				return menuType;
			}
		}
		
		return null;
	}

	@Override
	public synchronized Integer save(MenuType menuType) {
		String menuTypeId = menuType.getId().toString();
		
		//delete the old menutype
		Set<String> setId = jedis.keys("ib_menu_type:*:id");
		for (String key : setId) {
			String id = jedis.get(key);
			if (id.equals(menuTypeId)) {
				return 1;
			}
		}
		
		//save the new menutype
		String id = getNextId().toString();
		jedis.set("ib_menu_type:" + menuType.getName() + ":id", id);
		jedis.set("ib_menu_type:" + id + ":menu_type_name", menuType.getName());

		return 0;
	}

	@Override
	public synchronized void update(MenuType menuType) {
		String menuTypeId = menuType.getId().toString();
		
		//delete the old menutype
		Set<String> setId = jedis.keys("ib_menu_type:*:id");
		for (String key : setId) {
			String id = jedis.get(key);
			if (id.equals(menuTypeId)) {
				jedis.del(key);
				jedis.del("ib_menu_type:" + id + ":menu_type_name");
			}
		}
		
		//save the new menutype
		jedis.set("ib_menu_type:" + menuType.getName() + ":id", menuTypeId);
		jedis.set("ib_menu_type:" + menuTypeId + ":menu_type_name", menuType.getName());
	}

	@Override
	public synchronized void delete(MenuType menuType) {
		String menuTypeId = menuType.getId().toString();
		
		//delete the old menutype
		Set<String> setId = jedis.keys("ib_menu_type:*:id");
		for (String key : setId) {
			String id = jedis.get(key);
			if (id.equals(menuTypeId)) {
				jedis.del(key);
				jedis.del("ib_menu_type:" + id + ":menu_type_name");
			}
		}
	}

	@Override
	public synchronized List<MenuType> findAll() {
		ArrayList<MenuType> lstMenuType = new ArrayList<MenuType>();
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

	@Override
	public synchronized List<MenuType> findByName(String menuTypeName) {
		ArrayList<MenuType> lstMenuType = new ArrayList<MenuType>();
		Set<String> setId = jedis.keys("ib_menu_type:*:id");
		
		//iterator the keys
		for (String key : setId) {
			String id = jedis.get(key);
			String name = jedis.get("ib_menu_type:" + id + ":menu_type_name");

			if (name.equals(menuTypeName)) {
				MenuType menuType = new MenuType();
				menuType.setId(Integer.valueOf(id));
				menuType.setName(menuTypeName);
				
				lstMenuType.add(menuType);
			}
		}

		return lstMenuType;
	}
}
