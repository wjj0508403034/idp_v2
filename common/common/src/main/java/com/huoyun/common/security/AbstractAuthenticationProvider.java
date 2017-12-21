package com.huoyun.common.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public abstract class AbstractAuthenticationProvider implements AuthenticationProviderAdapter {

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public <T> T getBean(Class<T> requiredType) throws BeansException {
		return this.applicationContext.getBean(requiredType);
	}
}
