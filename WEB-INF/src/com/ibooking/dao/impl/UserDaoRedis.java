package com.ibooking.dao.impl;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import redis.clients.jedis.Jedis;

import com.ibooking.dao.*;
import com.ibooking.po.*;
import com.ibooking.util.*;

public class UserDaoRedis implements UserDao {
	private UserDao userDaoHbm;
	
	private Jedis jedis = null;
	
	public UserDaoRedis() {
		//create the redis connect
		jedis = new Jedis("127.0.0.1", 6379);
	}

	public void init() {
		//read and set the auto_increment value from mysql
		List<BigInteger> lstAutoInc = ((UserDaoHibernate)userDaoHbm).getAutoIncrement();
		if (lstAutoInc != null && lstAutoInc.size() != 0) {
			Integer autoInc = lstAutoInc.get(0).intValue();
			autoInc--;
			jedis.set("ib_user:auto_increment", autoInc.toString());
		}else {
			System.out.println("UserDaoRedis.UserDaoRedis() fail to read auto_increment from mysql");
		}
	}
	
	public Integer getNextId() {
		return jedis.incr("ib_user:auto_increment").intValue();
	}
	
	@Override
	public User get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(User user) throws RedisException {
		if (jedis != null) {
			String userId = jedis.get("ib_user:" + user.getName());
			if (userId == null) {
				userId = user.getId().toString();

				jedis.set("ib_user:" + user.getName(), userId);

				jedis.set("ib_user:" + userId + ":user_name", user.getName());
				jedis.set("ib_user:" + userId + ":user_passwd", user.getPasswd());
				jedis.set("ib_user:" + userId + ":user_auth", user.getAuth());
				jedis.set("ib_user:" + userId + ":user_tel", user.getTel());
				jedis.set("ib_user:" + userId + ":user_addr", user.getAddr());
			}else {
				throw new RedisException("user item is already exist");
			}
		}
		
		return 0;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByName(String name) {
		LinkedList<User> lstUser = new LinkedList<User>();
		User user = new User();
		
		if (jedis != null) {
			String userId = jedis.get("ib_user:" + name);
			if (userId != null) {
				String userPwd = jedis.get("ib_user:" + userId + ":user_passwd");
				String userAuth = jedis.get("ib_user:" + userId + ":user_auth");
				String userTel = jedis.get("ib_user:" + userId + ":user_tel");
				String userAddr = jedis.get("ib_user:" + userId + ":user_addr");
	
				user.setId(Integer.valueOf(userId));
				user.setName(name);
				user.setPasswd(userPwd);
				user.setAuth(userAuth);
				user.setTel(userTel);
				user.setAddr(userAddr);
				lstUser.add(user);
				
				return lstUser;
			}
		}

		return null;
	}

	public UserDao getUserDaoHbm() {
		return userDaoHbm;
	}

	public void setUserDaoHbm(UserDao userDaoHbm) {
		this.userDaoHbm = userDaoHbm;
	}
}
