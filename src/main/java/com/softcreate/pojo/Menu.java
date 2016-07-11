package com.softcreate.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 菜单
 * 
 * 
 * 用于生成手机端菜单时使用
 */
@Component
public class Menu {
	
	@Autowired
	private MenuButton[] button;

	public MenuButton[] getButton() {
		return button;
	}

	public void setButton(MenuButton[] button) {
		this.button = button;
	}
}