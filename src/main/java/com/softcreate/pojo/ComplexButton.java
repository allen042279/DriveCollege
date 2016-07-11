package com.softcreate.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 复杂按钮（父按钮）
 * 
 */
@Component
public class ComplexButton extends MenuButton {
	
	@Autowired
	private MenuButton[] sub_button;

	public MenuButton[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(MenuButton[] sub_button) {
		this.sub_button = sub_button;
	}
}