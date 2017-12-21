package com.huoyun.common.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public abstract class AbstractBusinessService implements BusinessService {

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public <T> T getBean(Class<T> requiredType) throws BeansException {
		return this.applicationContext.getBean(requiredType);
	}
}
