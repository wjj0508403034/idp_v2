package com.huoyun.common.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

public interface AuthenticationEventListener {

	void receive(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication);
}
