package com.huoyun.idp.login.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.huoyun.common.service.AbstractBusinessService;
import com.huoyun.idp.login.LoginHistory;
import com.huoyun.idp.login.LoginHistoryRepo;
import com.huoyun.idp.login.LoginHistoryService;
import com.huoyun.idp.login.LoginStatus;

@Service
public class LoginHistoryServiceImpl extends AbstractBusinessService implements LoginHistoryService {

	@Transactional
	@Override
	public void storeLoginRecord(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		LoginHistory history = this.newLoginHistory(request);
		history.setLoginAccount(this.getAccount(authentication));
		history.setLoginStatus(LoginStatus.SUCCESS);
		this.loginHistoryRepo().save(history);
	}

	@Transactional
	@Override
	public void storeLoginRecord(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {
		LoginHistory history = this.newLoginHistory(request);
		history.setLoginStatus(LoginStatus.FAILURE);
		history.setLoginAccount(this.getAccount(request));
		this.loginHistoryRepo().save(history);
	}

	private LoginHistory newLoginHistory(HttpServletRequest request) {
		LoginHistory history = new LoginHistory();
		history.setIp(this.getClientIp(request));
		history.setDevice(this.getDevice(request));
		history.setLoginDate(DateTime.now());
		return history;
	}

	private LoginHistoryRepo loginHistoryRepo() {
		return this.getBean(LoginHistoryRepo.class);
	}

	private String getClientIp(HttpServletRequest request) {

		String remoteAddr = request.getHeader("X-FORWARDED-FOR");
		if (StringUtils.isEmpty(remoteAddr)) {
			remoteAddr = request.getRemoteAddr();
		}

		return remoteAddr;
	}

	private String getDevice(HttpServletRequest request) {
		return request.getHeader("USER-AGENT");
	}

	private String getAccount(Authentication authentication) {
		if (authentication.getPrincipal() != null) {
			if (authentication.getPrincipal() instanceof User) {
				return ((User) authentication.getPrincipal()).getUsername();
			}
		}

		return null;
	}

	private String getAccount(HttpServletRequest request) {
		return request.getParameter("username");
	}

}
