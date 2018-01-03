package com.huoyun.common.log;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpRequestWrapper extends HttpServletRequestWrapper {

	private final Map<String, String> headers = new HashMap<>();

	public HttpRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public HttpRequestWrapper setHeader(String name, String value) {
		this.headers.put(name, value);
		return this;
	}

	@Override
	public String getHeader(String name) {
		// check the custom headers first
		String headerValue = this.headers.get(name);

		if (headerValue != null) {
			return headerValue;
		}
		// else return from into the original wrapped object
		return ((HttpServletRequest) getRequest()).getHeader(name);
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		// create a set of the custom header names
		Set<String> set = new HashSet<String>(this.headers.keySet());

		// now add the headers from the wrapped request object
		Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
		while (e.hasMoreElements()) {
			// add the names of the request headers into the list
			String n = e.nextElement();
			set.add(n);
		}

		// create an enumeration from the set and return
		return Collections.enumeration(set);
	}

}
