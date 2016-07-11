package com.softcreate.pojo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class WebSocketTextMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}