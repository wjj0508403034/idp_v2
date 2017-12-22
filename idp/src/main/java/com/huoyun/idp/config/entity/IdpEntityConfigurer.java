package com.huoyun.idp.config.entity;

import org.springframework.context.annotation.Configuration;

import com.huoyun.common.persistence.EntityConfigurer;
import com.huoyun.common.persistence.EntityConfigurerAdapter;

@Configuration
public class IdpEntityConfigurer extends EntityConfigurerAdapter {

	@Override
	protected void configure(EntityConfigurer configurer) {
		configurer.addListener(new IdpEntityChangedListener());
	}

}
