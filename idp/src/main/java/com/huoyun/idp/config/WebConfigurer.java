package com.huoyun.idp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huoyun.common.localization.EnableLocalization;
import com.huoyun.common.security.AuthenticationEventListener;
import com.huoyun.idp.config.authentication.IdpAuthenticationEventListener;

@EnableLocalization
@Configuration
public class WebConfigurer {

	@Bean
	public AuthenticationEventListener authenticationEventListener() {
		return new IdpAuthenticationEventListener();
	}
}
