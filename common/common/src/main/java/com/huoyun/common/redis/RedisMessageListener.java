package com.huoyun.common.redis;

import java.util.Collection;

import org.springframework.data.redis.listener.Topic;

public interface RedisMessageListener {

	static String HandlerMessageMethod = "onReceive";

	void onReceive(String message);

	Collection<? extends Topic> getTopics();
}
