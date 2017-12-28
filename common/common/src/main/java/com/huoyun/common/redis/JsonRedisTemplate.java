package com.huoyun.common.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class JsonRedisTemplate extends RedisTemplate<String, Object> {

	public JsonRedisTemplate() {
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		setKeySerializer(stringSerializer);
		setHashKeySerializer(stringSerializer);

		RedisJsonSerializer jsonSerializer = new RedisJsonSerializer();
		setValueSerializer(jsonSerializer);
		setHashValueSerializer(jsonSerializer);
	}

	public JsonRedisTemplate(RedisConnectionFactory connectionFactory) {
		this();
		setConnectionFactory(connectionFactory);
		afterPropertiesSet();
	}
}
