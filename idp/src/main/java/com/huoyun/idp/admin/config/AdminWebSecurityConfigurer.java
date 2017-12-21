package com.huoyun.idp.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.huoyun.idp.admin.authentication.AdminAuthenticationFilter;
import com.huoyun.idp.admin.authentication.AdminAuthenticationProvider;
import com.huoyun.idp.config.WebSecurityOrdered;

@Order(WebSecurityOrdered.ADMIN)
@Configuration
public class AdminWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.requestMatchers().antMatchers("/admin/**").and().authorizeRequests()
				.antMatchers(AdminUrls.LOGIN_PAGE_URL, AdminUrls.LOGIN_PROCESSING_URL).permitAll().and()
				.authorizeRequests().anyRequest().authenticated();
		http.exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(AdminUrls.LOGIN_PAGE_URL));
		http.addFilterAt(this.adminAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public AdminAuthenticationFilter adminAuthenticationFilter() throws Exception {
		AdminAuthenticationFilter filter = new AdminAuthenticationFilter();
		filter.setAuthenticationManager(this.authenticationManager);
		return filter;
	}

	@Bean
	public AdminAuthenticationProvider authenticationProvider() {
		return new AdminAuthenticationProvider();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.authenticationProvider());
	}
}
