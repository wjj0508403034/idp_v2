package com.huoyun.common.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationEventListener {

	void success(HttpServletRequest request, HttpServletResponse response, Authentication authentication);

	void failure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception);
}
