package com.huoyun.common.bo;

import org.springframework.data.domain.Page;
import com.huoyun.common.exceptions.BusinessException;

public interface BusinessObjectRepository<T> {

	Page<T> query(BusinessObjectSpecification<T> spec) throws BusinessException;

	void create(Object bo);
}
