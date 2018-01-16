package com.huoyun.common.metadata;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huoyun.common.metadata.impl.BusinessObjectMetadataRepositoryImpl;

@Configuration
public class BusinessObjectMetadataAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(BusinessObjectMetadataRepository.class)
	public BusinessObjectMetadataRepository businessObjectMetadataRepository() {
		return new BusinessObjectMetadataRepositoryImpl();
	}

}
