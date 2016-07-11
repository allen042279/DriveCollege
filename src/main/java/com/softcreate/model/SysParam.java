package com.softcreate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sysParam")
public class SysParam implements Serializable {

	private static final long serialVersionUID = 6556095710207897262L;

	@Id 
	@Column(name="id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="thisParamKey", unique = true, nullable = false)
	private String key;
	
	@Column(name="thisParamValue", nullable = false)
	private String value;
	
	
	public SysParam() {

	}
	
	public SysParam(Integer id, String key, String value) {
		super();
		this.id = id;
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
	
}
