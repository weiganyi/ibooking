package com.ibooking.dao.impl;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.ibooking.dao.*;
import com.ibooking.po.*;

public class MenuDaoRedis implements MenuDao {
	private MenuDao menuDaoHbm;
	
	private Jedis jedis = null;
	
	public MenuDaoRedis() {
		//create the redis connect
		jedis = new Jedis("127.0.0.1", 6379);
	}

	public void init() {
		//read and set the auto_increment value from mysql
		List<BigInteger> lstAutoInc = ((MenuDaoHibernate)menuDaoHbm).getAutoIncrement();
		if (lstAutoInc != null && lstAutoInc.size() != 0) {
			Integer autoInc = lstAutoInc.get(0).intValue();
			autoInc--;
			jedis.set("ib_menu:auto_increment", autoInc.toString());
		}else {
			System.out.println("MenuDaoRedis.init() fail to read auto_increment from mysql");
		}

		//read all record from mysql
		List<Menu> lstMenu = menuDaoHbm.findAll();
		for (Menu menu : lstMenu) {
			jedis.set("ib_menu:" + menu.getName() + ":id", menu.getId().toString());
			
			jedis.set("ib_menu:" + menu.getId().toString() + ":menu_name", menu.getName());
			jedis.set("ib_menu:" + menu.getId().toString() + ":menu_price", String.valueOf(menu.getPrice()));
			jedis.set("ib_menu:" + menu.getId().toString() + ":menu_pic_addr", menu.getPicture());
			jedis.set("ib_menu:" + menu.getId().toString() + ":menu_type_name", menu.getType().getName());
		}
	}

	public MenuDao getMenuDaoHbm() {
		return menuDaoHbm;
	}

	public void setMenuDaoHbm(MenuDao menuDaoHbm) {
		this.menuDaoHbm = menuDaoHbm;
	}
	
	public Integer getNextId() {
		return jedis.incr("ib_menu:auto_increment").intValue();
	}

	@Override
	public Menu get(Integer id) {
		return null;
	}

	@Override
	public Integer save(Menu menu) {
		return 0;
	}

	@Override
	public void update(Menu menu) {
	}

	@Override
	public void delete(Menu menu) {
	}

	@Override
	public List<Menu> findAll() {
		LinkedList<Menu> lstMenu = new LinkedList<Menu>();
		Set<String> setId = jedis.keys("ib_menu:*:id");
		
		//iterator the keys
		for (String key : setId) {
			String id = jedis.get(key);
			String name = jedis.get("ib_menu:" + id + ":menu_name");
			String price = jedis.get("ib_menu:" + id + ":menu_price");
			String addr = jedis.get("ib_menu:" + id + ":menu_pic_addr");
			String type = jedis.get("ib_menu:" + id + ":menu_type_name");
			
			String typeId = jedis.get("ib_menu_type:" + type + ":id");
			String typeName = jedis.get("ib_menu_type:" + typeId + ":menu_type_name");
			
			MenuType menuType = new MenuType();
			menuType.setId(Integer.valueOf(typeId));
			menuType.setName(typeName);

			Menu menu = new Menu();
			menu.setId(Integer.valueOf(id));
			menu.setName(name);
			menu.setPrice(Integer.valueOf(price));
			menu.setPicture(addr);
			menu.setType(menuType);
			
			lstMenu.add(menu);
		}

		return lstMenu;
	}
}
