package com.huoyun.idp.admin.account;

import org.springframework.security.core.userdetails.User;

public class AdminUserDetails extends User {

	private static final long serialVersionUID = 3773608573326667882L;

	public AdminUserDetails(AdminAccount account) {
		super(account.getEmail(), account.getPassword(), account.getGrantedAuthorityList());
	}

}
