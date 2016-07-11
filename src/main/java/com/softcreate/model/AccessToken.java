package com.softcreate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




/**
 * 微信通用接口凭证
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name="accessToken")
public class AccessToken implements Serializable {
	
	private static final long serialVersionUID = 1843667529366310840L;


	private String appId;
	private String appSecret;
	private String accessToken;
	private String expire;
	private Date addTimestamp;
	private int type;
	
	public AccessToken() {
		
	}
	
	public AccessToken(String appId, String appSecret, String accessToken,
			String expire, Date addTimestamp, int type) {
		this.appId = appId;
		this.appSecret = appSecret;
		this.setAccessToken(accessToken);
		this.expire = expire;
		this.addTimestamp = addTimestamp;
		this.type = type;
	}

//	@Id 
//	@Column(name="id", unique = true, nullable = false)
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
	
	@Id
	@Column(name="appId", length=255, unique = true, nullable = false)
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Column(nullable = false)
	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	@Column(length=512, nullable = false)
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	@Column(length=256, nullable = false)
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

	@Column(nullable = false)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
