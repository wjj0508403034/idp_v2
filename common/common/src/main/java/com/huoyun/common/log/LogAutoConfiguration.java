package com.huoyun.common.log;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogAutoConfiguration {

	
	@Bean
    public FilterRegistrationBean logFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LogFilter());
        registration.addUrlPatterns("/*");
        registration.setName("logFilter");
        registration.setOrder(Integer.MIN_VALUE);
        return registration;
    }


}
