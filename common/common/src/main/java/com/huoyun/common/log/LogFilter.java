package com.huoyun.common.log;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

public class LogFilter extends GenericFilterBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpRequestWrapper request = new HttpRequestWrapper((HttpServletRequest) req);
		HttpServletResponse response = (HttpServletResponse) res;
		final String traceId = this.getTraceId(request);
		final String sessionId = this.getSessionId(request);
		final String userName = this.getUserName(request, response);
		MDC.put("traceId", traceId);
		MDC.put("sessionId", sessionId);
		MDC.put("userName", userName);
		LOGGER.info("Received request: {}", request.getRequestURI());

		chain.doFilter(request, res);
	}

	private String getTraceId(HttpRequestWrapper request) {
		String traceId = request.getHeader(HttpTraceConstants.TRACE_ID);
		if (StringUtils.isEmpty(traceId)) {
			traceId = UUID.randomUUID().toString();
			request.setHeader(HttpTraceConstants.TRACE_ID, traceId);
		}
		return traceId;
	}

	private String getSessionId(HttpRequestWrapper request) {
		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			return httpSession.getId();
		}

		return null;
	}

	private String getUserName(HttpRequestWrapper request, HttpServletResponse response) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			return principal.getName();
		}

		return null;
	}

}
