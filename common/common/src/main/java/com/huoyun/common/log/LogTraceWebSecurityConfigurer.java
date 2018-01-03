package com.huoyun.common.log;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Order(50)
@Configuration
public class LogTraceWebSecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.addFilterAfter(new LogTraceFilterAfterAuthentication(), SecurityContextPersistenceFilter.class);

	}
}
