package com.huoyun.common.bo;

import org.springframework.data.domain.Page;

import com.huoyun.common.exceptions.BusinessException;

public interface BusinessObjectService {

	Page<BoData> query(String namespace, String name, String queryExpr) throws BusinessException;


}
