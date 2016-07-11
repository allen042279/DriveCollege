package com.softcreate.dao;

import com.softcreate.model.AccessToken;


public interface IAccessTokenDao {

	public AccessToken getSubAccecssToken(ISysParamDao sysParamDao);
	public AccessToken getCorpAccecssToken(ISysParamDao sysParamDao);
	
	public boolean add(AccessToken accessToken);
	
	public boolean isExpired(AccessToken accessToken);
	
}
