package com.softcreate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用于维护菜单，后台管理系统维护菜单时使用
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name="simpleMenu")
public class SimpleMenu implements Serializable {

	private static final long serialVersionUID = -7189881446478666331L;

	@Id 
	@Column(name="id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
    public static enum MenuType { view, click }  
    
    @Enumerated(value = EnumType.STRING)   
    @Column(nullable = false)
	private MenuType type;
    
    @Column(nullable = true)
	private String menuKey;
	
    @Column(nullable = false)
	private int parentId;
    
    @Transient
    private String parentName;
    
	@Column(nullable = false)
	private int showOrder;
	
	
	public SimpleMenu() {

	}
	
	public SimpleMenu(Integer id, String name, MenuType type, String key,
			int parentId, String parentName, int order) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.menuKey = key;
		this.parentId = parentId;
		this.parentName = parentName;
		this.showOrder = order;
	}
	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public MenuType getType() {
		return type;
	}

	public void setType(MenuType type) {
		this.type = type;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
