package com.huoyun.idp.admin.view;

import org.springframework.web.bind.annotation.RequestMapping;

import com.huoyun.common.mvc.ViewController;
import com.huoyun.idp.admin.config.AdminUrls;

@RequestMapping(value = AdminUrls.REQUEST_URL_PREFIX)
public abstract class AdminBaseController extends ViewController{
	
	protected String adminView(String viewName){
		return AdminUrls.REQUEST_URL_PREFIX + viewName;
	}
}
