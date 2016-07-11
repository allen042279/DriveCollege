package com.softcreate.pojo;

import org.springframework.stereotype.Component;

/**
 * 普通按钮（子按钮）
 * 
 */
@Component
public class CommonButton extends MenuButton {
	
	private String type;
	private String key;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}