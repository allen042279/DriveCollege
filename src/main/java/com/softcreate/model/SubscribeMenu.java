package com.softcreate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subscribeMenu")
public class SubscribeMenu implements Serializable {

	private static final long serialVersionUID = -8537913684492078308L;

	@Id 
	@Column(name="id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(length=1024, nullable = false)
	private String description;
	
	@Column(length=1024, nullable = false)
	private String picPath;  //图片保存的位置
	
	@Column(length=1024)
	private String url;   //图片指向的链接

	@Column(nullable = false)
	private boolean top;
	@Column(nullable = false)
	private boolean used;
	@Column(nullable = false)
	private int showOrder;
	
	
	public SubscribeMenu() {

	}

	public SubscribeMenu(Integer id, String title, String description,
			String picPath, String url, boolean top, boolean used, int order) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.picPath = picPath;
		this.url = url;
		this.top = top;
		this.used = used;
		this.showOrder = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
