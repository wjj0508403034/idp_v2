package com.huoyun.common.security;

public interface AuthenticationEventNotification {

	void addListener(AuthenticationEventListener listener);
}
