package com.huoyun.common.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

public class UIMetaConfigurer {

	private static final String DEAULT_UIMETA_LOCATION = "classpath:/META-INF/ui-meta/*.xml";

	@Value("${ui.meta.location:" + DEAULT_UIMETA_LOCATION + "}")
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@PostConstruct
	public void init() {

	}
}
