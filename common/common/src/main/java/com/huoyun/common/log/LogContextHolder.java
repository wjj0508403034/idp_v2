package com.huoyun.common.log;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.MDC;

public class LogContextHolder {

	private final static ThreadLocal<LogContext> localThread = new ThreadLocal<>();

	public static void set(String name, String value) {
		LogContext context = getLogContext();
		if (StringUtils.isEmpty(value)) {
			context.remove(name);
		} else {
			context.put(name, value);
		}

		MDC.put(name, value);
	}

	public static LogContext getLogContext() {
		LogContext context = localThread.get();
		if (context == null) {
			context = new LogContext();
			localThread.set(context);
		}
		return context;
	}
}
