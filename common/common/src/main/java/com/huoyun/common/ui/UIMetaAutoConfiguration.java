package com.huoyun.common.ui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huoyun.common.ui.impl.UIMetaLoaderImpl;
import com.huoyun.common.ui.metadata.UIMetadataRepository;
import com.huoyun.common.ui.metadata.impl.UIMetadataRepositoryImpl;

@Configuration
public class UIMetaAutoConfiguration {

	@Bean
	public UIMetaLoader uiMetaLoader() {
		return new UIMetaLoaderImpl();
	}

	@Bean
	public UIMetadataRepository uiMetadataRepository() {
		return new UIMetadataRepositoryImpl();
	}
}
