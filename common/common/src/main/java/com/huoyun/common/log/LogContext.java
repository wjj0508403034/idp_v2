package com.huoyun.common.log;

import java.util.concurrent.ConcurrentHashMap;

public class LogContext extends ConcurrentHashMap<String,String> {

	private static final long serialVersionUID = 7226610735660739222L;

	public static final String MDC_USERNAME = "userName";
	public static final String MDC_SESSION_ID = "sessionId";
}
