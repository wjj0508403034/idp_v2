package com.huoyun.common.bo;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.query.Query;
import com.huoyun.common.service.AbstractBusinessService;

public class BusinessObjectController extends AbstractBusinessService{

	
	@RequestMapping(value = "/bo({namespace},{name})", method = RequestMethod.GET)
	@ResponseBody
	public Page<BoData> query(@PathVariable(value = "namespace") String namespace,
			@PathVariable(value = "name") String name, @RequestParam(value = "query", required = false) String queryExpr)
			throws BusinessException {
		//Pageable pageable = new PageRequest(pageIndex, pageSize);
		//return this.businessObjectService.query(namespace, name, pageable, query, orderby);
		return this.businessObjectService().query(namespace, name, queryExpr);
	}
	
	
	private BusinessObjectService businessObjectService(){
		return this.getBean(BusinessObjectService.class);
	}
}
