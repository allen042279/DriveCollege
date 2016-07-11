package com.softcreate.dao;


import com.softcreate.model.JsApiTicket;

public interface IJSApiTicketDao {

	public JsApiTicket get(String accessToken);
	public boolean add(JsApiTicket jsApiTicket);
	
	public boolean isExpired(JsApiTicket jsApiTicket);
	
}
