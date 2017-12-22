package com.huoyun.idp.admin.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huoyun.common.mvc.ErrorForm;
import com.huoyun.common.mvc.ModelAttributeConstants;

@Controller
public class AdminViewController extends AdminBaseController {

	@RequestMapping(value = "/login.html", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginPage(@ModelAttribute(ModelAttributeConstants.FORM_ERRORS) ErrorForm errors) {
		return adminView("/login");
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String indexPage() {
		return adminView("/index");
	}
}
