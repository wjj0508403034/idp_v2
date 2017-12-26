package com.huoyun.common.bo;

import org.springframework.data.domain.Page;

import com.huoyun.common.bo.param.QueryParam;
import com.huoyun.common.exceptions.BusinessException;

public interface BusinessObjectService {

	Page<BoData> query(String namespace, String name, QueryParam queryParam) throws BusinessException;

	BoData createBo(String namespace, String name, BoData boData) throws BusinessException;
}
