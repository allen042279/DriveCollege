package com.softcreate.service;

import java.util.List;

import com.softcreate.model.AccessToken;
import com.softcreate.model.SimpleMenu;
import com.softcreate.pojo.Menu;

public interface IMenuService {

//	@RolesAllowed("ROLE_ADMIN")
	List<SimpleMenu> getAll();
	
//	@RolesAllowed("ROLE_ADMIN")
	SimpleMenu getMenu(int id);
	
//	@RolesAllowed("ROLE_ADMIN")
	List<SimpleMenu> getParentMenu();
	
//	@RolesAllowed("ROLE_ADMIN")
	long count();
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean addMenu(SimpleMenu simpleMenu);
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean deleteMenu(SimpleMenu simpleMenu);
	
//	@RolesAllowed("ROLE_ADMIN")
	boolean updateMenu(SimpleMenu simpleMenu);
	
//	@RolesAllowed("ROLE_ADMIN")
	AccessToken fetchSubAccessToken();
	
	AccessToken fetchCorpAccessToken();
	
//	@RolesAllowed("ROLE_ADMIN")
	Menu getMenu();
	
	
}
