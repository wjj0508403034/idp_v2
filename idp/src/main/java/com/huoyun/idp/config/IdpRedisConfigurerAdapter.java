package com.huoyun.idp.config;

import org.springframework.context.annotation.Configuration;

import com.huoyun.common.redis.EnableRedis;
import com.huoyun.common.redis.RedisConfigurer;
import com.huoyun.common.redis.RedisConfigurerAdapter;
import com.huoyun.idp.event.listener.LoginEventListener;

@EnableRedis
@Configuration
public class IdpRedisConfigurerAdapter extends RedisConfigurerAdapter {


	@Override
	protected void configure(RedisConfigurer redisConfigurer) {
		redisConfigurer.addReceiver(new LoginEventListener());
	}
}
