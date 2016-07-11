package com.softcreate.dao.daoImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.softcreate.dao.IAccessTokenDao;
import com.softcreate.dao.ISysParamDao;
import com.softcreate.model.AccessToken;

@Repository
@Scope("singleton")
public class AccessTokenImpl extends BaseDaoImpl<AccessToken> implements IAccessTokenDao {
	
	@Override
	public AccessToken getSubAccecssToken(ISysParamDao sysParamDao) {
		// TODO Auto-generated method stub
		return getAccessToken(1);
	}

	@Override
	public AccessToken getCorpAccecssToken(ISysParamDao sysParamDao) {
		// TODO Auto-generated method stub
		return getAccessToken(2);
	}
	
	private AccessToken getAccessToken(int type) {
		List<AccessToken> items = findAll(AccessToken.class);
		for (AccessToken accessToken : items) {
			if (accessToken.getType()==type) {
				return accessToken;
			}
		}
		
		return null;
	}

	public boolean isExpired(AccessToken accessToken) {

		Calendar calendar = Calendar.getInstance();
		Date currentTime = new Date();
		long nextTime = calendar.getTimeInMillis() - Integer.parseInt(accessToken.getExpire())*1000;
		currentTime.setTime(nextTime);
		
		if (currentTime.after(accessToken.getAddTimestamp())) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean add(AccessToken accessToken) {
		// TODO Auto-generated method stub
		try {
			save(accessToken);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
