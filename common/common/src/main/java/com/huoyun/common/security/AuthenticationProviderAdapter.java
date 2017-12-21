package com.huoyun.common.security;

import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationProvider;

public interface AuthenticationProviderAdapter extends AuthenticationProvider, ApplicationContextAware {

	
}
