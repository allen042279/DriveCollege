package com.softcreate.service.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.softcreate.dao.IAccessTokenDao;
import com.softcreate.dao.IMenuDao;
import com.softcreate.dao.ISysParamDao;
import com.softcreate.model.AccessToken;
import com.softcreate.model.SimpleMenu;
import com.softcreate.pojo.Menu;
import com.softcreate.pojo.WeixinUtil;
import com.softcreate.service.IMenuService;

@Service
public class MenuService implements IMenuService{

	@Resource private IMenuDao menuDao;
	@Resource private IAccessTokenDao accessTokenDao;
	@Resource private ISysParamDao sysParamDao;
	
	public List<SimpleMenu> getAll() {
		// TODO Auto-generated method stub
		return menuDao.getAll();
	}

	public SimpleMenu getMenu(int id) {
		// TODO Auto-generated method stub
		
		SimpleMenu simpleMenu = menuDao.getMenu(id);
		
		if(simpleMenu.getParentId()!=-1){
			SimpleMenu superMenu = menuDao.getMenu(simpleMenu.getParentId());
			simpleMenu.setParentName(superMenu.getName());
		}
		
		return simpleMenu;
	}

	public List<SimpleMenu> getParentMenu() {
		// TODO Auto-generated method stub
		return menuDao.getSuperMenu();
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return menuDao.count();
	}
	
	public boolean addMenu(SimpleMenu simpleMenu) {
		// TODO Auto-generated method stub
		return menuDao.saveMenu(simpleMenu);
	}

	public boolean deleteMenu(SimpleMenu simpleMenu) {
		// TODO Auto-generated method stub
		return menuDao.deleteMenu(simpleMenu);
	}

	public boolean updateMenu(SimpleMenu simpleMenu) {
		// TODO Auto-generated method stub
		return menuDao.updateMenu(simpleMenu);
	}

/***********************************************************************/
	public AccessToken fetchSubAccessToken() {
		// TODO Auto-generated method stub
		AccessToken accessToken = accessTokenDao.getSubAccecssToken(sysParamDao);
		if(accessTokenDao.isExpired(accessToken)){
			// 调用接口获取access_token
			accessToken = WeixinUtil.getSubAccessToken(accessToken.getAppId(), accessToken.getAppSecret());
			accessTokenDao.add(accessToken);
		}
		return accessToken;
	}

	public AccessToken fetchCorpAccessToken() {
		// TODO Auto-generated method stub
		AccessToken accessToken = accessTokenDao.getCorpAccecssToken(sysParamDao);
		if(accessTokenDao.isExpired(accessToken)){
			// 调用接口获取access_token
			accessToken = WeixinUtil.getCorpAccessToken(accessToken.getAppId(), accessToken.getAppSecret());
			accessTokenDao.add(accessToken);
		}
		return accessToken;
	}
	
	public Menu getMenu() {
		// TODO Auto-generated method stub
		return menuDao.get();
	}

}
