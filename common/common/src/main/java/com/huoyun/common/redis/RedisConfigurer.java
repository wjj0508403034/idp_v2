package com.huoyun.common.redis;

import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

public class RedisConfigurer {

	private RedisMessageListenerContainer listenerContainer;

	public void addReceiver(RedisMessageListener receiver) {
		if (this.listenerContainer == null) {
			throw new RuntimeException("RedisMessageListenerContainer not set");
		}
		
		MessageListenerAdapter messageListener = new MessageListenerAdapter(receiver,RedisMessageListener.HandlerMessageMethod);
		messageListener.afterPropertiesSet();
		
		this.listenerContainer.addMessageListener(messageListener, receiver.getTopics());
	}
	
	
	public RedisMessageListenerContainer getRedisMessageListenerContainer(){
		return this.listenerContainer;
	}

	public void setRedisMessageListenerContainer(RedisMessageListenerContainer listenerContainer) {
		this.listenerContainer = listenerContainer;
	}
}
