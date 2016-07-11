package com.softcreate.dao.daoImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.softcreate.dao.IJSApiTicketDao;
import com.softcreate.model.JsApiTicket;
import com.softcreate.pojo.WeixinUtil;

@Repository
@Scope("singleton")
public class JSApiTicketDaoImpl extends BaseDaoImpl<JsApiTicket> implements IJSApiTicketDao {
	
	public JsApiTicket get(String accessToken) {
		// TODO Auto-generated method stub
		JsApiTicket item = null;
		
		List<JsApiTicket> items = findAll(JsApiTicket.class);
		
		if (items.size()==0) {
			item = WeixinUtil.getJSApiTicket(accessToken);
			add(item);
		}else {
			item = items.get(0);
		}
		
		return item;
	}
	
	public boolean isExpired(JsApiTicket jsApiTicket) {

		Calendar calendar = Calendar.getInstance();
		Date currentTime = new Date();
		long nextTime = calendar.getTimeInMillis() - Integer.parseInt(jsApiTicket.getExpire())*1000;
		currentTime.setTime(nextTime);
		
		if (currentTime.after(jsApiTicket.getAddTimestamp())) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean add(JsApiTicket jsApiTicket) {
		// TODO Auto-generated method stub
		try {
			save(jsApiTicket);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
