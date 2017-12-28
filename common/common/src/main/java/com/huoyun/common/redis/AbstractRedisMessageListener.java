package com.huoyun.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public abstract class AbstractRedisMessageListener implements RedisMessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRedisMessageListener.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

	public AbstractRedisMessageListener() {
		this.objectMapper.registerModule(new JodaModule());
		this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	public void onReceive(String message) {
		LOGGER.info("Received message: {}", message);
		this.onMessage(message);
	}

	public abstract void onMessage(String message);

	protected <T> T entity(String message, Class<T> entityClass) {
		try {
			return this.objectMapper.readValue(message.getBytes(), entityClass);
		} catch (Exception e) {
			throw new RuntimeException("String to entity failed.", e);
		}
	}
}
