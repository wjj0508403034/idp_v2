package com.huoyun.idp.admin.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminViewController {

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loginPage() {
		return "admin/login";
	}
}
