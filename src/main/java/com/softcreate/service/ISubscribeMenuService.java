package com.softcreate.service;

import java.util.List;

import com.softcreate.model.SubscribeMenu;

public interface ISubscribeMenuService {
	
	List<SubscribeMenu> getAll();
	
//	@RolesAllowed("ROLE_ADMIN")
	List<SubscribeMenu> getPage(int page, int pageSize);
	
//	@RolesAllowed("ROLE_ADMIN")
	SubscribeMenu getMenu(int id);
	
//	@RolesAllowed("ROLE_ADMIN")
	long count();
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean addMenu(SubscribeMenu subscribeMenu);
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean deleteMenu(SubscribeMenu subscribeMenu);
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean updateMenu(SubscribeMenu subscribeMenu);
	
}
