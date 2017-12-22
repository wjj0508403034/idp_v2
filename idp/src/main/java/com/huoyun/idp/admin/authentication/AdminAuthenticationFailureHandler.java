package com.huoyun.idp.admin.authentication;

import com.huoyun.idp.config.authentication.CommonAuthenticationFailureHandler;

public class AdminAuthenticationFailureHandler extends CommonAuthenticationFailureHandler {

	public AdminAuthenticationFailureHandler(String forwardUrl) {
		super(forwardUrl);
	}
}
