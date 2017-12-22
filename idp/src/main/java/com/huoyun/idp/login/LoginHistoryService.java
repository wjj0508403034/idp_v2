package com.huoyun.idp.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface LoginHistoryService {

	void storeLoginRecord(HttpServletRequest request, HttpServletResponse response, Authentication authentication);

	void storeLoginRecord(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception);

}
