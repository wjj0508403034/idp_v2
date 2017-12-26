package com.huoyun.common.bo;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huoyun.common.bo.param.QueryParam;
import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.service.AbstractBusinessService;

public class BusinessObjectController extends AbstractBusinessService {

	@RequestMapping(value = "/bo({namespace},{name})", method = RequestMethod.GET)
	@ResponseBody
	public Page<BoData> query(@PathVariable(value = "namespace") String namespace,
			@PathVariable(value = "name") String name, QueryParam queryParam)
			throws BusinessException {
		return this.businessObjectService().query(namespace, name, queryParam);
	}
	
	
	@RequestMapping(value = "/bo({namespace},{name})/create", method = RequestMethod.POST)
	@ResponseBody
	public BoData create(@PathVariable(value = "namespace") String namespace,
			@PathVariable(value = "name") String name, @RequestBody BoData boData) throws BusinessException {
		return this.businessObjectService().createBo(namespace, name, boData);
	}


	private BusinessObjectService businessObjectService() {
		return this.getBean(BusinessObjectService.class);
	}
}