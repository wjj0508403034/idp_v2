package com.huoyun.common.log;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class LogTraceFilterAfterAuthentication extends GenericFilterBean{

	private static final Logger LOGGER = LoggerFactory.getLogger(LogTraceFilterAfterAuthentication.class);
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		final String userName = this.getUserName(request, response);
		final String sessionId = this.getSessionId(request);
		LogContextHolder.set(LogContext.MDC_USERNAME, userName);
		LogContextHolder.set(LogContext.MDC_SESSION_ID, sessionId);
		this.setCookie(response, LogContext.MDC_USERNAME, userName);
		LOGGER.info("Current user name: {}",userName);
		chain.doFilter(request, response);
	}
	
	private String getUserName(HttpServletRequest request, HttpServletResponse response) {
		SecurityContext context = SecurityContextHolder.getContext();
		if(context.getAuthentication() != null){
			return context.getAuthentication().getName();
		}
		
		return null;
	}
	
	private void setCookie(HttpServletResponse response,String name,String value){
		Cookie cookie = new Cookie("userName", value);
		response.addCookie(cookie);
	}
	
	
	private String getSessionId(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			return httpSession.getId();
		}

		return null;
	}

}
