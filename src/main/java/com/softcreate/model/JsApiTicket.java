package com.softcreate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;




/**
 * 微信通用接口凭证
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name="JsApiTicket")
public class JsApiTicket implements Serializable {
	
	private static final long serialVersionUID = -2024011601886863983L;
	
	private String jsapi_ticket;
	private String expire;
	private Date addTimestamp;
	
	//这是此注解后该属性不会数据持久化也是本例要说明的注解   
	@Transient
	private String nonceStr;
	@Transient
	private String timestamp;
	@Transient
	private String signature;
	@Transient
	private String appId;
	
	public JsApiTicket() {
		
	}
	
	public JsApiTicket(String jsapi_ticket, String expire, Date addTimestamp) {
		super();
		this.jsapi_ticket = jsapi_ticket;
		this.expire = expire;
		this.addTimestamp = addTimestamp;
	}

	@Id @Column(name="jsapi_ticket", length=255, unique = true, nullable = false)
	public String getJsapiTicket() {
		return jsapi_ticket;
	}

	public void setJsapiTicket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}

	@Column(length=218, nullable = false)
	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAddTimestamp() {
		return addTimestamp;
	}

	public void setAddTimestamp(Date addTimestamp) {
		this.addTimestamp = addTimestamp;
	}
	
	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
