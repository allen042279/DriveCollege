package com.softcreate.dao.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.softcreate.dao.IMenuDao;
import com.softcreate.model.SimpleMenu;
import com.softcreate.model.SimpleMenu.MenuType;
import com.softcreate.pojo.CommonButton;
import com.softcreate.pojo.ComplexButton;
import com.softcreate.pojo.Menu;
import com.softcreate.pojo.MenuButton;
import com.softcreate.pojo.ViewButton;

@Repository
@Scope("singleton")
public class MenuDaoImpl  extends BaseDaoImpl<SimpleMenu> implements IMenuDao {
	
	public Menu get() {
		// TODO Auto-generated method stub
		Menu menus = new Menu();
		
		List<SimpleMenu> subButtonList = find("From SimpleMenu as s where s.parentId!=-1");
		List<SimpleMenu> parentButtonList = find("From SimpleMenu as s where s.parentId=-1");
			
		for (SimpleMenu simpleMenu : parentButtonList) {
			simpleMenu.setParentId(0);  //此处用于统计子菜单数量
		}
				
		//统计每个父菜单下的子菜单数量
		for (SimpleMenu menuItem : subButtonList) {
			
			int parentId = menuItem.getParentId();
			
			for (SimpleMenu parentButton : parentButtonList) {
				
				if (parentId==parentButton.getId()) {

					parentButton.setParentId(parentButton.getParentId()+1);
					break;
				}
			}
		}
				
		//转换数据
		int j;
		int count = parentButtonList.size();
		ComplexButton[] parentMenuButton = new ComplexButton[count];
		
		for (int i=0; i<count; i++ ) {
			
			ComplexButton complexButton = new ComplexButton();
			complexButton.setName(parentButtonList.get(i).getName());
			
			int subCount = parentButtonList.get(i).getParentId();
			int id = parentButtonList.get(i).getId();
			
			MenuButton[] subMenuButton = new MenuButton[subCount];
			j = 0;
			
			for (SimpleMenu menuItem : subButtonList) {
				
				int parentId = menuItem.getParentId();
				
				if(id==parentId ){
					
					if (MenuType.click==menuItem.getType()) {
						
						CommonButton commonButton = new CommonButton();
						
						commonButton.setName(menuItem.getName());
						commonButton.setType(menuItem.getType()==MenuType.click?"click":"view");
						commonButton.setKey(menuItem.getMenuKey());
						
						subMenuButton[j++] = commonButton;
						
					}else{
						
						ViewButton viewButton = new ViewButton();
						
						viewButton.setName(menuItem.getName());
						viewButton.setType(menuItem.getType()==MenuType.click?"click":"view");
						viewButton.setUrl(menuItem.getMenuKey());
						
						subMenuButton[j++] = viewButton;
					}
					
				}
			}
			
			complexButton.setSub_button(subMenuButton);
			parentMenuButton[i] = complexButton;
		}
				
		menus.setButton(parentMenuButton);
		
		return menus;
	}

	
	
	public List<SimpleMenu> getSuperMenu() {

		return find("From SimpleMenu as s where s.parentId = -1 "); 
		
	}


	public SimpleMenu getMenuByKey(String key) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuKey", key);
		
		return get(SimpleMenu.class, "From SimpleMenu as s where s.menukey=:menuKey ", map);
		
	}
	
	public List<SimpleMenu> getTop() {
		// TODO Auto-generated method stub
		return find("From SimpleMenu as s where s.is_used = 1 and s.is_top = 1 ");
	}

	public List<SimpleMenu> getSup() {
		// TODO Auto-generated method stub
		return find("From SimpleMenu as s where s.is_used = 1 order by s.myorder ");
	}

	public long count() {
		// TODO Auto-generated method stub
		return findCount(SimpleMenu.class);
	}

	public List<SimpleMenu> getAll() {
		// TODO Auto-generated method stub
		List<SimpleMenu> simpleMenus = findAll(SimpleMenu.class);
		for (SimpleMenu simpleMenu : simpleMenus) {
			if (-1!=simpleMenu.getParentId()) {
				simpleMenu.setParentName(getById(SimpleMenu.class, simpleMenu.getParentId()).getName());
			}
		}
		return simpleMenus;
	}

	public SimpleMenu getMenu(int id) {
		// TODO Auto-generated method stub
		return getById(SimpleMenu.class, id);
	}
	
	public boolean saveMenu(SimpleMenu simpleMenu) {
		// TODO Auto-generated method stub
		try {
			save(simpleMenu);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean updateMenu(SimpleMenu simpleMenu) {
		// TODO Auto-generated method stub
		try {
			update(simpleMenu);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean deleteMenu(SimpleMenu simpleMenu) {
		// TODO Auto-generated method stub
		try {
			delete(simpleMenu);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
