package com.huoyun.idp.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.huoyun.idp.config.WebSecurityOrdered;

@Order(WebSecurityOrdered.USER)
@Configuration
public class UserWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().csrf().disable();

		http.requestMatchers().antMatchers("/**").and().authorizeRequests().antMatchers("/login.html", "/login")
				.permitAll().and().authorizeRequests().anyRequest().authenticated().and().formLogin()
				.loginPage("/login.html").loginProcessingUrl("/login").defaultSuccessUrl("/index.html");
	}
}
