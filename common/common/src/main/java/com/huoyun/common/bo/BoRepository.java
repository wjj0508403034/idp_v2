package com.huoyun.common.bo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.huoyun.common.exceptions.BusinessException;

public interface BoRepository<T> {

	Page<T> query(BoSpecification<T> spec, Pageable pageable) throws BusinessException;
}
