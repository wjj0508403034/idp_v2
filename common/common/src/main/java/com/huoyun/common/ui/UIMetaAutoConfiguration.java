package com.huoyun.common.ui;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.huoyun.common.metadata.BusinessObjectMetadataAutoConfiguration;
import com.huoyun.common.ui.ext.ListViewExt;
import com.huoyun.common.ui.ext.ListViewExtRepository;
import com.huoyun.common.ui.ext.UIMetaExtService;
import com.huoyun.common.ui.ext.impl.UIMetaExtServiceImpl;
import com.huoyun.common.ui.impl.UIMetaLoaderImpl;
import com.huoyun.common.ui.metadata.UIMetadataRepository;
import com.huoyun.common.ui.metadata.impl.UIMetadataRepositoryImpl;

@EntityScan(basePackageClasses = { ListViewExt.class })
@EnableJpaRepositories(basePackageClasses = { ListViewExtRepository.class })
@Configuration
@AutoConfigureAfter(BusinessObjectMetadataAutoConfiguration.class)
public class UIMetaAutoConfiguration {

	@Bean
	public UIMetaLoader uiMetaLoader() {
		return new UIMetaLoaderImpl();
	}

	@Bean
	public UIMetaExtService uiMetaExtService() {
		return new UIMetaExtServiceImpl();
	}

	@ConditionalOnMissingBean(UIMetadataRepository.class)
	@Bean
	public UIMetadataRepository uiMetadataRepository() {
		return new UIMetadataRepositoryImpl();
	}
}
