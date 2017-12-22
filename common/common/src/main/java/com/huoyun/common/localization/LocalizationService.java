package com.huoyun.common.localization;

import com.huoyun.common.exceptions.BusinessException;

public interface LocalizationService {

	String getErrorMessage(BusinessException businessException, Object[] args);

	String getErrorMessage(BusinessException businessException);

	String getMessage(String messageKey, Object[] args);

	String getMessage(String messageKey);
}
