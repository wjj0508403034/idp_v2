package com.huoyun.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class RedisJsonSerializer implements RedisSerializer<Object> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisJsonSerializer.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	public RedisJsonSerializer() {
		this.objectMapper.registerModule(new JodaModule());
		this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	@Override
	public byte[] serialize(Object data) throws SerializationException {

		try {
			final String str = this.objectMapper.writeValueAsString(data);
			return str.getBytes();
		} catch (JsonProcessingException e) {
			LOGGER.warn("Serialize to json string failed", e);
			throw new SerializationException("Cannot deserialize", e);
		}
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		return bytes;
	}

}
