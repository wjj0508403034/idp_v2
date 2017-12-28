package com.huoyun.idp.event.listener;

import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.Topic;

import com.huoyun.common.redis.AbstractRedisMessageListener;
import com.huoyun.idp.event.LoginEvent;

public class LoginEventListener extends AbstractRedisMessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginEventListener.class);

	@Override
	public Collection<? extends Topic> getTopics() {
		return Arrays.asList(new PatternTopic("chat"));
	}

	@Override
	public void onMessage(String message) {
		LoginEvent loginEvent = this.entity(message, LoginEvent.class);
		LOGGER.info("User {} login at {} successfully.", loginEvent.getEmail(), loginEvent.getLoginTime());
	}

}
