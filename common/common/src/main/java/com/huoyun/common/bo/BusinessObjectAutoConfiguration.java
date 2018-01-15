package com.huoyun.common.bo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.huoyun.common.bo.impl.BusinessObjectFacadeImpl;
import com.huoyun.common.bo.impl.BusinessObjectMapperImpl;
import com.huoyun.common.bo.impl.BusinessObjectServiceImpl;
import com.huoyun.common.exceptions.ErrorHandler;
import com.huoyun.common.metadata.BusinessObjectMetadataRepository;
import com.huoyun.common.metadata.impl.BusinessObjectMetadataRepositoryImpl;
import com.huoyun.common.ui.UIMetaAutoConfiguration;

@Import({ ErrorHandler.class ,UIMetaAutoConfiguration.class})
@Configuration
public class BusinessObjectAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(BusinessObjectMapper.class)
	public BusinessObjectMapper businessObjectMapper() {
		return new BusinessObjectMapperImpl();
	}

	@Bean
	@ConditionalOnMissingBean(BusinessObjectMetadataRepository.class)
	public BusinessObjectMetadataRepository businessObjectMetadataRepository() {
		return new BusinessObjectMetadataRepositoryImpl();
	}

	@Bean
	@ConditionalOnMissingBean(BusinessObjectFacade.class)
	public BusinessObjectFacade businessObjectFacade() {
		return new BusinessObjectFacadeImpl();
	}
	
	@Bean
	@ConditionalOnMissingBean(BusinessObjectService.class)
	public BusinessObjectService businessObjectService(){
		return new BusinessObjectServiceImpl();
	}
}
