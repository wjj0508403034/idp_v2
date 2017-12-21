package com.huoyun.idp.user.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserViewController {

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loginPage() {
		return "user/login";
	}
}
