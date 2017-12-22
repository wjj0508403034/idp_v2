package com.huoyun.idp.config.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.security.AuthenticationFailureHandler;
import com.huoyun.idp.common.ErrorCodes;

public class CommonAuthenticationFailureHandler extends AuthenticationFailureHandler{

	public CommonAuthenticationFailureHandler(String forwardUrl) {
		super(forwardUrl);
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		request.setAttribute("username", request.getParameter("username"));
		this.clearErrors();
		if (exception instanceof UsernameNotFoundException) {
			this.addErrorMessage(request, "username", new BusinessException(ErrorCodes.Login_Account_Not_Found));
		} else if (exception instanceof BadCredentialsException) {
			this.addErrorMessage(request, "password", new BusinessException(ErrorCodes.Login_Bad_Credentials));
		} else if (exception instanceof LockedException) {
			this.addErrorMessage(request, "username", new BusinessException(ErrorCodes.Login_Account_Locked));
		} else {
			this.addErrorMessage(request, "username", new BusinessException(ErrorCodes.Login_Failed));
		}

		super.onAuthenticationFailure(request, response, exception);
	}

}
