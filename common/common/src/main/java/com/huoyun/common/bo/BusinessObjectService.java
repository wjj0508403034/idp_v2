package com.huoyun.common.bo;

import org.springframework.data.domain.Page;

import com.huoyun.common.bo.param.QueryParam;
import com.huoyun.common.exceptions.BusinessException;

public interface BusinessObjectService {

	Page<BoData> query(String namespace, String name, QueryParam queryParam) throws BusinessException;
	
	Long count(String namespace, String name, QueryParam queryParam) throws BusinessException;

	BoData createBo(String namespace, String name, BoData boData) throws BusinessException;

	BoData updateBo(String namespace, String name, Long id, BoData boData) throws BusinessException;

	BoData load(String namespace, String name, Long id) throws BusinessException;

	void delete(String namespace, String name, Long id) throws BusinessException;
}
