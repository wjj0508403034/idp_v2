package com.huoyun.common.localization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huoyun.common.localization.impl.LocalizationServiceImpl;

@Configuration
public class LocalizationConfigurer {

	@Bean
	public LocalizationService localizationService() {
		return new LocalizationServiceImpl();
	}
}
