package com.softcreate.dao.daoImpl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.softcreate.dao.ISysParamDao;
import com.softcreate.model.AccessToken;
import com.softcreate.model.SysParam;
import com.softcreate.pojo.WeixinUtil;

@Repository
@Scope("singleton")
public class SysParamDaoImpl extends BaseDaoImpl<SysParam> implements ISysParamDao{

	public AccessToken getSubAccessToken() {
		
		List<SysParam> sysParams = find("From SysParam as s where s.key='appid' or s.key='appsecret'");
		
		String appid = "";
		String appsecret = "";
		
		for (SysParam sysParam : sysParams) {
			
			String key = sysParam.getKey();
			String value = sysParam.getValue();
			
			if("appid".equals(key)){
				appid = value;
			}
			if ("appsecret".equals(key)) {
				appsecret = value;
			}
		}
		
		return WeixinUtil.getSubAccessToken(appid, appsecret);
	}

	@Override
	public AccessToken getCorpAccessToken() {
		
		List<SysParam> sysParams = find("From SysParam as s where s.key='corpid' or s.key='corpsecret'");
		
		String corpid = "";
		String corpsecret = "";
		
		for (SysParam sysParam : sysParams) {
			
			String key = sysParam.getKey();
			String value = sysParam.getValue();
			
			if("corpid".equals(key)){
				corpid = value;
			}
			if ("corpsecret".equals(key)) {
				corpsecret = value;
			}
		}
		
		return WeixinUtil.getCorpAccessToken(corpid, corpsecret);
	}

	@Override
	public String getToken() {

		List<SysParam> sysParams = find("From SysParam as s where s.key='token' ");
		
		String token = "";
		
		for (SysParam sysParam : sysParams) {
			
			String key = sysParam.getKey();
			String value = sysParam.getValue();
			
			if("token".equals(key)){
				token = value;
			}
		}
		
		return token;
	}

}
