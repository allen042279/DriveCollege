package com.softcreate.dao;

import java.util.List;

import com.softcreate.model.SimpleMenu;
import com.softcreate.pojo.Menu;

public interface IMenuDao {

	/**
	 * 用于生成手机端菜单时使用
	 * @return
	 */
	Menu get();
	
	/**
	 * 用于维护菜单，后台管理系统使用
	 * @param item
	 * @return
	 */
	List<SimpleMenu> getSuperMenu();


	SimpleMenu getMenuByKey(String key);

	long count();
	List<SimpleMenu> getAll();
	SimpleMenu getMenu(int id);
	
	boolean saveMenu(SimpleMenu simpleMenu);
	boolean updateMenu(SimpleMenu simpleMenu);
	boolean deleteMenu(SimpleMenu simpleMenu);

}
