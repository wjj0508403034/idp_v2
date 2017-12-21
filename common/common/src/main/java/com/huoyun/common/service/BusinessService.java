package com.huoyun.common.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;

public interface BusinessService extends ApplicationContextAware {

	<T> T getBean(Class<T> requiredType) throws BeansException;
}
