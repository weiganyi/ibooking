package com.ibooking.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import com.ibooking.dao.*;
import com.ibooking.po.*;

public class TransDaoRedis implements TransDao{
	private Jedis jedis = null;
	
	public TransDaoRedis() {
		//create the redis connect
		jedis = new Jedis("127.0.0.1", 6379);
	}

	@Override
	public synchronized void deleteMenuType(List<Menu> lstOldMenu, MenuType oldMenuType, MenuType newMenuType) {
		ArrayList<String> delLst = new ArrayList<String>();
		HashMap<String, String> setMap = new HashMap<String, String>();
		
		//update all menus to new type
		for (Menu menu : lstOldMenu) {
			menu.setType(newMenuType);

			String menuId = menu.getId().toString();
			
			//delete the old menu
			Set<String> setId = jedis.keys("ib_menu:*:id");
			for (String key : setId) {
				String id = jedis.get(key);
				if (id.equals(menuId)) {
					delLst.add(key);
					delLst.add("ib_menu:" + id + ":menu_name");
					delLst.add("ib_menu:" + id + ":menu_price");
					delLst.add("ib_menu:" + id + ":menu_pic_addr");
					delLst.add("ib_menu:" + id + ":menu_type_id");
				}
			}
			
			//save the new menu
			setMap.put("ib_menu:" + menu.getName() + ":id", menuId);
			setMap.put("ib_menu:" + menuId + ":menu_name", menu.getName());
			setMap.put("ib_menu:" + menuId + ":menu_price", String.valueOf(menu.getPrice()));
			setMap.put("ib_menu:" + menuId + ":menu_pic_addr", menu.getPicture());
			setMap.put("ib_menu:" + menuId + ":menu_type_id", String.valueOf(menu.getType().getId()));
		}
		
		//delete the menutype from redis
		String menuTypeId = oldMenuType.getId().toString();

		Set<String> setId = jedis.keys("ib_menu_type:*:id");
		for (String key : setId) {
			String id = jedis.get(key);
			if (id.equals(menuTypeId)) {
				//delete the old menutype
				delLst.add(key);
				delLst.add("ib_menu_type:" + id + ":menu_type_name");
			}
		}
		
		//run the transtion
		Transaction tx = jedis.multi();
		for (String cmd : delLst) {
			tx.del(cmd);
		}
		Set<String> keySet = setMap.keySet();
		for (String key : keySet) {
			tx.set(key, setMap.get(key));
		}
		tx.exec();
		
		return;
	}
}
