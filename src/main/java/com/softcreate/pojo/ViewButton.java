package com.softcreate.pojo;

import org.springframework.stereotype.Component;

/**
 * view类型的菜单
 * 
 */
@Component
public class ViewButton extends MenuButton {
	
	private String type;
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}