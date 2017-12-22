package com.huoyun.common.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;

public class AuthenticationFailureHandler extends ForwardAuthenticationFailureHandler
		implements AuthenticationEventNotification {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureHandler.class);

	private final List<AuthenticationEventListener> authenticationListeners = new ArrayList<>();

	public AuthenticationFailureHandler(String forwardUrl) {
		super(forwardUrl);

	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		LOGGER.warn("Login failed", exception);

		for (AuthenticationEventListener listener : authenticationListeners) {
			listener.failure(request, response, exception);
		}

		super.onAuthenticationFailure(request, response, exception);
	}

	@Override
	public void addListener(AuthenticationEventListener listener) {
		this.authenticationListeners.add(listener);
	}

}
