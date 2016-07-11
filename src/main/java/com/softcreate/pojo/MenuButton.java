package com.softcreate.pojo;

import org.springframework.stereotype.Component;

/**
 * 按钮的基类
 * 
 */
@Component
public class MenuButton {
	
	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}