package com.huoyun.idp.admin.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.huoyun.common.security.AbstractAuthenticationProvider;
import com.huoyun.idp.admin.account.AdminAccount;
import com.huoyun.idp.admin.account.AdminAccountService;
import com.huoyun.idp.admin.account.AdminUserDetails;

public class AdminAuthenticationProvider extends AbstractAuthenticationProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminAuthenticationProvider.class);

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		AdminAuthenticationToken token = (AdminAuthenticationToken) authentication;
		LOGGER.info("Admin {} start login ...", token.getUsername());
		AdminAccount account = this.adminAccountService().findAccountByEmail(token.getUsername());
		if (account == null) {
			LOGGER.warn("Admin {} not found.", token.getUsername());
			throw new UsernameNotFoundException(token.getUsername());
		}

		if (!this.passwordEncoder.matches((String) authentication.getCredentials(), account.getPassword())) {
			LOGGER.warn("Admin {} password invalid.", token.getUsername());
			throw new BadCredentialsException("Bad credentials");
		}
		
		if(account.isLocked()){
			LOGGER.warn("Admin {} locked.", token.getUsername());
			throw new LockedException("User locked");
		}
		
		AdminUserDetails userDetails = new AdminUserDetails(account);
		
		token.setDetails(userDetails);
		token.setAuthorities(userDetails.getAuthorities());
		authentication.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(AdminAuthenticationToken.class);
	}

	private AdminAccountService adminAccountService() {
		return this.getBean(AdminAccountService.class);
	}

}
