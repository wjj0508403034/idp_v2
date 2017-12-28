package com.huoyun.common.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.huoyun.common.localization.LocalizationService;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler{
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);
	
	@Autowired
	private LocalizationService localizationService;
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> BusinessError(BusinessException businessException) 
	{
		if (StringUtils.isEmpty(businessException.getMessage())) {
			businessException.setMessage(this.localizationService.getErrorMessage(businessException));
		}
		
		LOGGER.error("Internal System Error.", businessException);
		return new ResponseEntity<Object>(new BusinessExceptionResponse(businessException), HttpStatus.BAD_REQUEST);
	}
}
