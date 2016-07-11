package com.softcreate.service.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.softcreate.dao.ISubscribeMenuDao;
import com.softcreate.model.SubscribeMenu;
import com.softcreate.service.ISubscribeMenuService;

@Service
public class SubscribeMenuService implements ISubscribeMenuService{

	@Resource 
	private ISubscribeMenuDao subscribeMenuDao;
	
	@Override
	public List<SubscribeMenu> getAll() {
		// TODO Auto-generated method stub
		return subscribeMenuDao.getAll();
	}
	
	public List<SubscribeMenu> getPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return subscribeMenuDao.getPage(pageNo, pageSize);
	}

	public SubscribeMenu getMenu(int id) {
		// TODO Auto-generated method stub
		return subscribeMenuDao.getMenu(id);
	}

	public long count() {
		// TODO Auto-generated method stub
		return subscribeMenuDao.count();
	}
	
	public boolean addMenu(SubscribeMenu subscribeMenu) {
		// TODO Auto-generated method stub
		return subscribeMenuDao.saveMenu(subscribeMenu);
	}

	public boolean deleteMenu(SubscribeMenu subscribeMenu) {
		// TODO Auto-generated method stub
		return subscribeMenuDao.deleteMenu(subscribeMenu);
	}

	public boolean updateMenu(SubscribeMenu subscribeMenu) {
		// TODO Auto-generated method stub
		return subscribeMenuDao.updateMenu(subscribeMenu);
	}
}
