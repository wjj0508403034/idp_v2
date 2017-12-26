package com.huoyun.common.bo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.metadata.annotation.BusinessObject;

public interface BoRepository<T extends BusinessObject> {

	Page<T> query(BoSpecification<T> spec, Pageable pageable) throws BusinessException;

	//Page<? extends BusinessObject> query(BoSpecification<? extends BusinessObject> boSpec, Object pageable);
}
