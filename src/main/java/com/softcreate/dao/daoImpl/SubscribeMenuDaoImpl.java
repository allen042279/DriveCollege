package com.softcreate.dao.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.softcreate.dao.ISubscribeMenuDao;
import com.softcreate.model.SubscribeMenu;

@Repository
@Scope("singleton")
public class SubscribeMenuDaoImpl extends BaseDaoImpl<SubscribeMenu> implements ISubscribeMenuDao {

	public List<SubscribeMenu> getTop() {
		// TODO Auto-generated method stub
		String hql = "from SubscribeMenu as s where s.used = :used and s.top = :top";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("used", Boolean.TRUE);
		params.put("top", Boolean.TRUE);
		
		return find(hql, params);
	}

	public List<SubscribeMenu> getSup() {
		// TODO Auto-generated method stub
		String hql = "from SubscribeMenu as s where s.used = :used and s.top = :top order by s.showOrder";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("used", Boolean.TRUE); 
		params.put("top", Boolean.FALSE);
		
		return find(hql, params);
	}

	@Override
	public List<SubscribeMenu> getAll() {
		// TODO Auto-generated method stub
		return find("From SubscribeMenu as s Where s.used = 1 ");
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return findCount(SubscribeMenu.class);
	}

	public List<SubscribeMenu> getPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return findByPage("From SubscribeMenu as s Order By s.showOrder", pageNo, pageSize);
	}

	public SubscribeMenu getMenu(int id) {
		// TODO Auto-generated method stub
		return getById(SubscribeMenu.class, id);
	}
	
	public boolean saveMenu(SubscribeMenu subscribeMenu) {
		// TODO Auto-generated method stub
		try {
			save(subscribeMenu);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean updateMenu(SubscribeMenu subscribeMenu) {
		// TODO Auto-generated method stub
		try {
			update(subscribeMenu);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean deleteMenu(SubscribeMenu subscribeMenu) {
		// TODO Auto-generated method stub
		try {
			delete(subscribeMenu);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
