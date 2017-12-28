package com.huoyun.common.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(RedisConfigurerAdapter.class)
	public RedisConfigurerAdapter redisConfigurerAdapter(RedisMessageListenerContainer listenerContainer) {
		RedisConfigurerAdapter adapter =  new RedisConfigurerAdapter();
		adapter.setRedisMessageListenerContainer(listenerContainer);
		return adapter;
	}

	@Bean
	public JsonRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new JsonRedisTemplate(connectionFactory);
	}

	@Bean("defaultRedisMessageListnerContainer")
	@ConditionalOnMissingBean(RedisMessageListenerContainer.class)
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		return container;
	}
}
