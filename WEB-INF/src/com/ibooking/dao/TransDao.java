package com.ibooking.dao;

import java.util.List;

import com.ibooking.po.*;

public interface TransDao {
	void deleteMenuType(List<Menu> lstOldMenu, MenuType oldMenuType, MenuType newMenuType);
}
