package com.huoyun.idp.admin.config;

public final class AdminUrls {

	public final static String REQUEST_URL_PREFIX = "/admin";
	public final static String[] URL_MATCH_PATTERNS = new String[] { REQUEST_URL_PREFIX + "/**" };
	public final static String LOGIN_PAGE_URL = REQUEST_URL_PREFIX + "/login.html";
	public final static String LOGIN_PROCESSING_URL = REQUEST_URL_PREFIX + "/login";
	public final static String LOGIN_SUCCESS_URL = REQUEST_URL_PREFIX + "/index.html";
	public final static String LOGIN_FAILURE_URL = LOGIN_PAGE_URL;
	public final static String[] PERMIT_ALL_URLS = new String[] { LOGIN_PAGE_URL, LOGIN_PROCESSING_URL };
}
