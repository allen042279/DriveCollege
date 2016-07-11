package com.softcreate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name="preStudent")
public class PreStudent implements Serializable {

	private static final long serialVersionUID = 2514612379805181701L;

	@Id
	@Column(name="id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String username;
	
	//1 中职教育, 2 学历教育, 3 会计培训, 4 其他咨询       
    public static enum SexType { 中职教育, 学历教育, 会计培训, 其他咨询}
    
    @Enumerated(value = EnumType.ORDINAL)  //ORDINAL序数 
	@Column(nullable=false)
	private SexType sexType;
	
	@Column(length=56, nullable = false)
	@Size(min=10, max=50)
	private String mobile;

	@Column(length=1024, nullable = false)
	private String intent;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date applyDate;
	
	@Column(nullable = false)
	private boolean dealwith;
	
	public PreStudent() {

	}

	public PreStudent(Integer id, String username, SexType sexType, String mobile,
			String intent, Date applyDate, boolean dealwith) {
		super();
		this.id = id;
		this.username = username;
		this.sexType = sexType;
		this.mobile = mobile;
		this.intent = intent;
		this.applyDate = applyDate;
		this.dealwith = dealwith;
	}

	public boolean isDealwith() {
		return dealwith;
	}

	public void setDealwith(boolean dealwith) {
		this.dealwith = dealwith;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public SexType getSexType() {
		return sexType;
	}

	public void setSexType(SexType sexType) {
		this.sexType = sexType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

}
