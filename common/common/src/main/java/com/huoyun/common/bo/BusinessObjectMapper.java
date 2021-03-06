package com.huoyun.common.bo;

import org.springframework.data.domain.Page;
import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.query.Query;

public interface BusinessObjectMapper {

	BoData mapper(Object bo, BusinessObjectMetadata boMeta) throws BusinessException;
	
	BoData mapper(Object value, BusinessObjectMetadata boMeta, Query query) throws BusinessException;

	Page<BoData> mapper(Page<?> pageData, BusinessObjectMetadata boMeta, Query query) throws BusinessException;

	void merge(Object bo, BoData boData, BusinessObjectMetadata boMeta) throws BusinessException;

	
}
