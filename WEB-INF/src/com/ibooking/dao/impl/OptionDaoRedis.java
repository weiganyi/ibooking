package com.ibooking.dao.impl;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

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
		return null;
	}

	@Override
	public synchronized Integer save(Option option){
		if (jedis != null) {
			String optionId = jedis.get("ib_option:" + option.getName() + ":id");
			if (optionId == null) {
				optionId = option.getId().toString();

				jedis.set("ib_option:" + option.getName() + ":id", optionId);

				jedis.set("ib_option:" + optionId + ":option_name", option.getName());
				jedis.set("ib_option:" + optionId + ":option_value", option.getValue());
				
				return 1;
			}
		}
		
		return 0;
	}

	@Override
	public synchronized void update(Option option) {
	}

	@Override
	public synchronized void delete(Option option) {
	}

	@Override
	public synchronized List<Option> findAll() {
		return null;
	}

	@Override
	public synchronized List<Option> findByName(String name) {
		LinkedList<Option> lstOption = new LinkedList<Option>();
		Option option = new Option();
		
		if (jedis != null) {
			String optionId = jedis.get("ib_option:" + name + ":id");
			if (optionId != null) {
				String optionValue = jedis.get("ib_option:" + optionId + ":option_value");
	
				option.setId(Integer.valueOf(optionId));
				option.setName(name);
				option.setValue(optionValue);
				lstOption.add(option);
				
				return lstOption;
			}
		}

		return null;
	}
}
