package com.huoyun.idp.admin.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminViewController extends AdminBaseController {

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loginPage() {
		return adminView("/login");
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String indexPage() {
		return adminView("/index");
	}
}
