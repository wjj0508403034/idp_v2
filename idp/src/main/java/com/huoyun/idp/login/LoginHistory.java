package com.huoyun.idp.login;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.huoyun.common.persistence.BaseEntity;

@Entity
@Table
public class LoginHistory extends BaseEntity {

	private static final long serialVersionUID = -6304477610270410409L;

	private String loginAccount;

	private LoginStatus loginStatus;

	private DateTime loginDate;

	private String ip;

	private String device;

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public LoginStatus getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}

	public DateTime getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(DateTime loginDate) {
		this.loginDate = loginDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

}
