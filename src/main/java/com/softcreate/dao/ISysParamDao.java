package com.softcreate.dao;

import com.softcreate.model.AccessToken;



public interface ISysParamDao {

	public AccessToken getSubAccessToken();
	
	public AccessToken getCorpAccessToken();
	
	public String getToken();
	
}
