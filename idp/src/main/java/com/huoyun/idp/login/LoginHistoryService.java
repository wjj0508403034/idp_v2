package com.huoyun.idp.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

public interface LoginHistoryService {

	void storeLoginRecord(HttpServletRequest request, HttpServletResponse response, Authentication authentication);

}
