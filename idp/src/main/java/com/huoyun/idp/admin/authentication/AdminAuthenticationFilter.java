package com.huoyun.idp.admin.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.huoyun.idp.admin.config.AdminUrls;

public class AdminAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public AdminAuthenticationFilter() {
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(AdminUrls.LOGIN_PROCESSING_URL, "POST"));
		this.authenticationSuccessHandler().setDefaultTargetUrl(AdminUrls.LOGIN_SUCCESS_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		AdminAuthenticationToken authRequest = new AdminAuthenticationToken(username, password);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() {
		return (SavedRequestAwareAuthenticationSuccessHandler) this.getSuccessHandler();
	}
}
