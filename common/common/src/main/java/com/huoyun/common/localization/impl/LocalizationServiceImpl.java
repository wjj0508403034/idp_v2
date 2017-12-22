package com.huoyun.common.localization.impl;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.localization.LocalizationService;
import com.huoyun.common.service.AbstractBusinessService;

public class LocalizationServiceImpl extends AbstractBusinessService implements LocalizationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocalizationServiceImpl.class);

	private static final String DEFAULT_BUSINESS_EXCEPTION_MESSAGE_PREFIX = "Error_";

	private String businessExceptionMessagePrefix = DEFAULT_BUSINESS_EXCEPTION_MESSAGE_PREFIX;
	private boolean fallback = true;

	@Override
	public String getErrorMessage(BusinessException businessException) {
		return this.getErrorMessage(businessException, null);
	}

	@Override
	public String getErrorMessage(BusinessException businessException, Object[] args) {
		if (StringUtils.isEmpty(businessException.getCode())) {
			throw new RuntimeException("No set error code for business exception");
		}

		return this.getMessage(this.getErrorMessageKey(businessException), args);
	}

	@Override
	public String getMessage(String messageKey) {
		return this.getMessage(messageKey, null);
	}

	@Override
	public String getMessage(String messageKey, Object[] args) {
		try {
			return this.messageSource().getMessage(messageKey, args, Locale.CHINA);
		} catch (Exception ex) {
			LOGGER.warn("Not found {} string in localization file", messageKey);
			if (this.isFallback()) {
				return this.getFallbackMessage(messageKey);
			}

			throw new RuntimeException(String.format("Not found {%s} string in localization file", messageKey));
		}
	}

	private String getFallbackMessage(String messageKey) {
		return "%" + messageKey + "%";
	}

	private String getErrorMessageKey(BusinessException businessException) {
		return this.getBusinessExceptionMessagePrefix() + businessException.getCode();
	}

	private MessageSource messageSource() {
		return this.getBean(MessageSource.class);
	}

	public String getBusinessExceptionMessagePrefix() {
		return businessExceptionMessagePrefix;
	}

	public void setBusinessExceptionMessagePrefix(String businessExceptionMessagePrefix) {
		this.businessExceptionMessagePrefix = businessExceptionMessagePrefix;
	}

	public boolean isFallback() {
		return fallback;
	}

	public void setFallback(boolean fallback) {
		this.fallback = fallback;
	}

}
