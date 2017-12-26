package com.huoyun.common.metadata;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Import;

import com.huoyun.common.bo.impl.BusinessObjectFacadeImpl;
import com.huoyun.common.bo.impl.BusinessObjectServiceImpl;
import com.huoyun.common.metadata.impl.BusinessObjectMetadataRepositoryImpl;

@Import({ BusinessObjectMetadataRepositoryImpl.class, BusinessObjectServiceImpl.class, BusinessObjectFacadeImpl.class })
public abstract class BusinessObjectMetadataConfigurerAdapter {

	private final BusinessObjectMetadataConfigurer boMetaConfigurer = new BusinessObjectMetadataConfigurer();

	protected void configure(BusinessObjectMetadataConfigurer configurer) {

	}

	@PostConstruct
	public void init() {
		this.configure(this.boMetaConfigurer);
	}

	public BusinessObjectMetadataConfigurer getBoMetaConfigurer() {
		return this.boMetaConfigurer;
	}
}
