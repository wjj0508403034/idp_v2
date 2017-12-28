package com.huoyun.idp.config.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.huoyun.common.redis.JsonRedisTemplate;
import com.huoyun.common.security.AuthenticationEventListener;
import com.huoyun.common.service.AbstractBusinessService;
import com.huoyun.idp.event.LoginEvent;
import com.huoyun.idp.login.LoginHistoryService;

public class IdpAuthenticationEventListener extends AbstractBusinessService implements AuthenticationEventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(IdpAuthenticationEventListener.class);

	@Override
	public void success(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		LOGGER.info("Store login record ...");
		this.loginHistoryService().storeLoginRecord(request, response, authentication);
		LoginEvent loginEvent = new LoginEvent();
		loginEvent.setUsername(request.getParameter("username"));
		loginEvent.setLoginTime(DateTime.now());
		loginEvent.setEmail(request.getParameter("username"));
		this.redisTemplate().convertAndSend("chat", loginEvent);
		LOGGER.info("Store login record done.");
	}

	@Override
	public void failure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
		LOGGER.info("Store login record ...");
		this.loginHistoryService().storeLoginRecord(request, response, exception);
		LOGGER.info("Store login record done.");
	}

	private LoginHistoryService loginHistoryService() {
		return this.getBean(LoginHistoryService.class);
	}
	
	private JsonRedisTemplate redisTemplate(){
		return this.getBean(JsonRedisTemplate.class);
	}

}
