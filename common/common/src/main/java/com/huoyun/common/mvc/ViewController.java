package com.huoyun.common.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class ViewController {

	@ModelAttribute(ModelAttributeConstants.FORM_ERRORS)
	public ErrorForm getErrorForm(HttpServletRequest request) {
		ErrorForm form = (ErrorForm) request.getAttribute(ModelAttributeConstants.FORM_ERRORS);
		if (form == null) {
			form = new ErrorForm();
		}
		return form;
	}

}
