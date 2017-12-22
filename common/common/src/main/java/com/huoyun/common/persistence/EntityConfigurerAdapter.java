package com.huoyun.common.persistence;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Import;

@Import({ EntityCrudAspect.class })
public abstract class EntityConfigurerAdapter implements InitializingBean {

	private final EntityConfigurer configurer = new EntityConfigurer();

	protected void configure(EntityConfigurer configurer) {

	}

	@PostConstruct
	public void init() {
		this.configure(this.configurer);
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public EntityConfigurer getEntityConfigurer() {
		return this.configurer;
	}
}
