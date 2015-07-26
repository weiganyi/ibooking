package com.ibooking.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.ibooking.dao.*;
import com.ibooking.po.*;

public class OptionDaoRedis implements OptionDao {
	private OptionDao optionDaoHbm;
	
	private Jedis jedis = null;
	
	public OptionDaoRedis() {
		//create the redis connect
		jedis = new Jedis("127.0.0.1", 6379);
	}

	public void init() {
		//read and set the auto_increment value from mysql
		List<BigInteger> lstAutoInc = ((OptionDaoHibernate)optionDaoHbm).getAutoIncrement();
		if (lstAutoInc != null && lstAutoInc.size() != 0) {
			Integer autoInc = lstAutoInc.get(0).intValue();
			autoInc--;
			jedis.set("ib_option:auto_increment", autoInc.toString());
		}else {
			System.out.println("OptionDaoRedis.init() fail to read auto_increment from mysql");
		}
		
		//read all record from mysql
		List<Option> lstOption = optionDaoHbm.findAll();
		for (Option option : lstOption) {
			jedis.set("ib_option:" + option.getName() + ":id", option.getId().toString());
			
			jedis.set("ib_option:" + option.getId().toString() + ":option_name", option.getName());
			jedis.set("ib_option:" + option.getId().toString() + ":option_value", option.getValue());
		}
	}

	public OptionDao getUserDaoHbm() {
		return optionDaoHbm;
	}

	public void setOptionDaoHbm(OptionDao optionDaoHbm) {
		this.optionDaoHbm = optionDaoHbm;
	}

	public synchronized Integer getNextId() {
		return jedis.incr("ib_option:auto_increment").intValue();
	}
	
	@Override
	public synchronized Option get(Integer id) {
		Set<String> setId = jedis.keys("ib_option:*:id");
		
		//iterator the keys
		for (String key : setId) {
			String id2 = jedis.get(key);

			if (Integer.valueOf(id2) == id) {
				String name = jedis.get("ib_option:" + id + ":option_name");
				String value = jedis.get("ib_option:" + id + ":option_value");

				Option option = new Option();
				option.setId(Integer.valueOf(id));
				option.setName(name);
				option.setValue(value);
				
				return option;
			}
		}

		return null;
	}

	@Override
	public synchronized Integer save(Option option){
		String optionId = option.getId().toString();
		
		//delete the old option
		Set<String> setId = jedis.keys("ib_option:*:id");
		for (String key : setId) {
			String id = jedis.get(key);
			if (id.equals(optionId)) {
				return 1;
			}
		}
		
		//save the new option
		String id = getNextId().toString();
		jedis.set("ib_option:" + option.getName() + ":id", id);
		jedis.set("ib_option:" + id + ":option_name", option.getName());
		jedis.set("ib_option:" + id + ":option_value", option.getValue());

		return 0;
	}

	@Override
	public synchronized void update(Option option) {
		String optionId = option.getId().toString();
		
		//delete the old option
		Set<String> setId = jedis.keys("ib_option:*:id");
		for (String key : setId) {
			String id = jedis.get(key);
			if (id.equals(optionId)) {
				jedis.del(key);
				jedis.del("ib_option:" + id + ":option_name");
				jedis.del("ib_option:" + id + ":option_value");
			}
		}
		
		//save the new option
		jedis.set("ib_option:" + option.getName() + ":id", optionId);
		jedis.set("ib_option:" + optionId + ":option_name", option.getName());
		jedis.set("ib_option:" + optionId + ":option_value", option.getValue());
	}

	@Override
	public synchronized void delete(Option option) {
		String optionId = option.getId().toString();
		
		//delete the old option
		Set<String> setId = jedis.keys("ib_option:*:id");
		for (String key : setId) {
			String id = jedis.get(key);
			if (id.equals(optionId)) {
				jedis.del(key);
				jedis.del("ib_option:" + id + ":option_name");
				jedis.del("ib_option:" + id + ":option_value");
			}
		}
	}

	@Override
	public synchronized List<Option> findAll() {
		ArrayList<Option> lstOption = new ArrayList<Option>();
		Set<String> setId = jedis.keys("ib_option:*:id");
		
		//iterator the keys
		for (String key : setId) {
			String id = jedis.get(key);
			String name = jedis.get("ib_option:" + id + ":option_name");
			String value = jedis.get("ib_option:" + id + ":option_value");

			Option option = new Option();
			option.setId(Integer.valueOf(id));
			option.setName(name);
			option.setValue(value);
			
			lstOption.add(option);
		}

		return lstOption;
	}

	@Override
	public synchronized List<Option> findByName(String optionName) {
		ArrayList<Option> lstOption = new ArrayList<Option>();
		Set<String> setId = jedis.keys("ib_option:*:id");
		
		//iterator the keys
		for (String key : setId) {
			String id = jedis.get(key);
			String name = jedis.get("ib_option:" + id + ":option_name");

			if (name.equals(optionName)) {
				String value = jedis.get("ib_option:" + id + ":option_value");

				Option option = new Option();
				option.setId(Integer.valueOf(id));
				option.setName(name);
				option.setValue(value);
				
				lstOption.add(option);
			}
		}

		return lstOption;
	}
}
