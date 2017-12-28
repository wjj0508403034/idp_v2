package com.huoyun.common.redis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

public class RedisConfigurerAdapter implements InitializingBean, ApplicationContextAware {

	private ApplicationContext applicationContext;
	private final RedisConfigurer redisConfigurer = new RedisConfigurer();
	private RedisMessageListenerContainer listenerContainer;

	public void setRedisMessageListenerContainer(RedisMessageListenerContainer listenerContainer) {
		this.listenerContainer = listenerContainer;
	}

	protected void configure(RedisConfigurer redisConfigurer) {

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.listenerContainer == null) {
			this.listenerContainer = this.applicationContext.getBean("defaultRedisMessageListnerContainer",RedisMessageListenerContainer.class);
		}
		this.redisConfigurer.setRedisMessageListenerContainer(listenerContainer);
		this.configure(this.redisConfigurer);
	}

	public RedisConfigurer getRedisConfigurer() {
		return this.redisConfigurer;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
