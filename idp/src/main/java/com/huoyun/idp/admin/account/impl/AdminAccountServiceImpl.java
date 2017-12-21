package com.huoyun.idp.admin.account.impl;

import org.springframework.stereotype.Service;

import com.huoyun.common.service.AbstractBusinessService;
import com.huoyun.idp.admin.account.AdminAccount;
import com.huoyun.idp.admin.account.AdminAccountRepo;
import com.huoyun.idp.admin.account.AdminAccountService;

@Service
public class AdminAccountServiceImpl extends AbstractBusinessService implements AdminAccountService {

	@Override
	public AdminAccount findAccountByEmail(String email) {
		return this.adminAccountRepo().findAccountByEmail(email);
	}

	private AdminAccountRepo adminAccountRepo() {
		return this.getBean(AdminAccountRepo.class);
	}

}
