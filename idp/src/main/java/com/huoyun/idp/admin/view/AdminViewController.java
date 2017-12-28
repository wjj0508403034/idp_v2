package com.huoyun.idp.admin.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huoyun.common.mvc.ErrorForm;
import com.huoyun.common.mvc.ModelAttributeConstants;
import com.huoyun.common.redis.JsonRedisTemplate;
import com.huoyun.idp.admin.account.AdminAccount;

@Controller
public class AdminViewController extends AdminBaseController {

	@Autowired
	private JsonRedisTemplate redisTemplate;
	
	@RequestMapping(value = "/login.html", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginPage(@ModelAttribute(ModelAttributeConstants.FORM_ERRORS) ErrorForm errors) {
		return adminView("/login");
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String indexPage() {
		return adminView("/index");
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public void test() {
		AdminAccount adminAccount = new AdminAccount();
		adminAccount.setEmail("jingjing1");
		this.redisTemplate.convertAndSend("chat", adminAccount);
	}
}
