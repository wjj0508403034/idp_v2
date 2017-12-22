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

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.localization.LocalizationService;
import com.huoyun.common.mvc.ErrorForm;
import com.huoyun.common.mvc.ModelAttributeConstants;

public class AuthenticationFailureHandler extends ForwardAuthenticationFailureHandler
		implements AuthenticationEventNotification {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureHandler.class);
	private final String forwardUrl;
	private final List<AuthenticationEventListener> authenticationListeners = new ArrayList<>();
	private LocalizationService localizationService;
	private final ErrorForm errors = new ErrorForm();

	public AuthenticationFailureHandler(String forwardUrl) {
		super(forwardUrl);
		this.forwardUrl = forwardUrl;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		LOGGER.warn("Login failed", exception);
		request.setAttribute(ModelAttributeConstants.FORM_ERRORS, this.getErrors());

		for (AuthenticationEventListener listener : authenticationListeners) {
			listener.failure(request, response, exception);
		}

		super.onAuthenticationFailure(request, response, exception);
	}

	@Override
	public void addListener(AuthenticationEventListener listener) {
		this.authenticationListeners.add(listener);
	}

	public String getForwardUrl() {
		return this.forwardUrl;
	}

	public ErrorForm getErrors() {
		return this.errors;
	}
	
	public void clearErrors(){
		this.getErrors().clear();
	}

	public LocalizationService getLocalizationService() {
		return localizationService;
	}

	public void setLocalizationService(LocalizationService localizationService) {
		this.localizationService = localizationService;
	}

	protected void addErrorMessage(HttpServletRequest request, String name, BusinessException ex) {
		this.getErrors().addField(name, this.getLocalizationService().getErrorMessage(ex));
	}

}
