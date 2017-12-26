package com.huoyun.common.bo;

import org.springframework.data.domain.Page;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.metadata.annotation.BusinessObject;

public interface BusinessObjectService {

	Page<BoData> query(String namespace, String name, String queryExpr) throws BusinessException;


}
