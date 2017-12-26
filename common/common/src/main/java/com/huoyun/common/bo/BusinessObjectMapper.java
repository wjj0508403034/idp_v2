package com.huoyun.common.bo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.metadata.BusinessObjectMetadata;

public interface BusinessObjectMapper {

	BoData mapper(Object value, BusinessObjectMetadata boMeta) throws BusinessException;

	Page<BoData> mapper(Page<?> pageData, BusinessObjectMetadata boMeta, Pageable pageable) throws BusinessException;
}
