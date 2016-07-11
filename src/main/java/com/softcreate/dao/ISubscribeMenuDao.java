package com.softcreate.dao;

import java.util.List;

import com.softcreate.model.SubscribeMenu;

public interface ISubscribeMenuDao {

	List<SubscribeMenu> getTop();
	List<SubscribeMenu> getSup();
	
	List<SubscribeMenu> getAll();
	
	long count();
	List<SubscribeMenu> getPage(int pageNo, int pageSize);
	SubscribeMenu getMenu(int id);
	
	boolean saveMenu(SubscribeMenu subscribeMenu);
	boolean updateMenu(SubscribeMenu subscribeMenu);
	boolean deleteMenu(SubscribeMenu subscribeMenu);
	
}
