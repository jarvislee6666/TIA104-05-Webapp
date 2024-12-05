package com.admin.model;

import java.sql.Date;
import java.sql.Timestamp;

public class AdmVO implements java.io.Serializable{

	private Integer admId;//admin_id
	private String admEmail;//admin_email
	private String admPassword;//admin_password
	private String admName;//admin_name
	private String admPhone;//admin_phone
	private Date hrDate;//hire_date
	private Boolean admSta;//admin_state
	private Date admBday;//admin_birthday
	private Boolean supvsr;//supervisor
	
	public Integer getAdmId() {
		return admId;
	}
	public void setAdmId(Integer admId) {
		this.admId = admId;
	}
	public String getAdmEmail() {
		return admEmail;
	}
	public void setAdmEmail(String admEmail) {
		this.admEmail = admEmail;
	}
	public String getAdmPassword() {
		return admPassword;
	}
	public void setAdmPassword(String admPassword) {
		this.admPassword = admPassword;
	}
	public String getAdmName() {
		return admName;
	}
	public void setAdmName(String admName) {
		this.admName = admName;
	}
	public String getAdmPhone() {
		return admPhone;
	}
	public void setAdmPhone(String admPhone) {
		this.admPhone = admPhone;
	}
	public Date getHrDate() {
		return hrDate;
	}
	public void setHrDate(Date hrDate) {
		this.hrDate = hrDate;
	}
	public Boolean getAdmSta() {
		return admSta;
	}
	public void setAdmSta(Boolean admSta) {
		this.admSta = admSta;
	}
	public Date getAdmBday() {
		return admBday;
	}
	public void setAdmBday(Date admBday) {
		this.admBday = admBday;
	}
	public Boolean getSupvsr() {
		return supvsr;
	}
	public void setSupvsr(Boolean supvsr) {
		this.supvsr = supvsr;
	}
	
	
	
	
	
	
}
