package com.huoyun.common.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
		implements AuthenticationEventNotification {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

	private final List<AuthenticationEventListener> authenticationListeners = new ArrayList<>();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		LOGGER.info("Login success");

		for (AuthenticationEventListener listener : authenticationListeners) {
			listener.success(request, response, authentication);
		}

		super.onAuthenticationSuccess(request, response, authentication);
	}

	@Override
	public void addListener(AuthenticationEventListener listener) {
		this.authenticationListeners.add(listener);
	}
}
