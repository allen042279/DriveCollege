package com.softcreate.service.ServiceImpl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softcreate.dao.IJSApiTicketDao;
import com.softcreate.model.AccessToken;
import com.softcreate.model.JsApiTicket;
import com.softcreate.pojo.WeixinUtil;
import com.softcreate.service.IJSService;
import com.softcreate.service.IMenuService;

@Service
@Transactional
public class JSService implements IJSService {

	@Resource private IJSApiTicketDao jsApiTicketDao ;
	@Resource private IMenuService menuService;

	@Override
	public JsApiTicket getConfig(String url) {
		// TODO Auto-generated method stub
		AccessToken accessToken = menuService.fetchSubAccessToken();
		JsApiTicket jsApiTicket = jsApiTicketDao.get(accessToken.getAccessToken());
		if(jsApiTicketDao.isExpired(jsApiTicket)){
			// 调用接口获取access_token
			jsApiTicket = WeixinUtil.getJSApiTicket(accessToken.getAccessToken());
			jsApiTicketDao.add(jsApiTicket);
		}
		
		Map<String, String> map = WeixinUtil.sign(jsApiTicket.getJsapiTicket(), url);
		
		jsApiTicket.setAppId(accessToken.getAppId());
		jsApiTicket.setNonceStr(map.get("nonceStr"));
		jsApiTicket.setSignature(map.get("signature"));
		jsApiTicket.setTimestamp(map.get("timestamp"));
		
		return jsApiTicket;
	}
	
	
}
