package com.huoyun.idp.config;

import org.springframework.context.annotation.Configuration;

import com.huoyun.common.metadata.BusinessObjectMetadataConfigurer;
import com.huoyun.common.metadata.BusinessObjectMetadataConfigurerAdapter;

@Configuration
public class IdpBusinessObjectMetadataConfigurer extends BusinessObjectMetadataConfigurerAdapter {

	@Override
	protected void configure(BusinessObjectMetadataConfigurer configurer) {

	}
}
