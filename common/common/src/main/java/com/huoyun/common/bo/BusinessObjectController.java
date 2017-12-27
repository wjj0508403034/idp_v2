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

	@RequestMapping(value = "/bo({namespace},{name})/create", method = RequestMethod.POST)
	@ResponseBody
	public BoData create(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "name") String name,
			@RequestBody BoData boData) throws BusinessException {
		return this.businessObjectService().createBo(namespace, name, boData);
	}

	@RequestMapping(value = "/bo({namespace},{name})/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BoData load(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "name") String name,
			@PathVariable(value = "id") Long id) throws BusinessException {
		return this.businessObjectService().load(namespace, name, id);
	}

	@RequestMapping(value = "/bo({namespace},{name})/{id}", method = RequestMethod.PATCH)
	@ResponseBody
	public BoData update(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "name") String name,
			@PathVariable(value = "id") Long id, @RequestBody BoData boData) throws BusinessException {
		return this.businessObjectService().updateBo(namespace, name, id, boData);
	}

	@RequestMapping(value = "/bo({namespace},{name})/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "name") String name,
			@PathVariable(value = "id") Long id) throws BusinessException {
		this.businessObjectService().delete(namespace, name, id);
	}

	@RequestMapping(value = "/bo({namespace},{name})", method = RequestMethod.GET)
	@ResponseBody
	public Page<BoData> query(@PathVariable(value = "namespace") String namespace,
			@PathVariable(value = "name") String name, QueryParam queryParam) throws BusinessException {
		return this.businessObjectService().query(namespace, name, queryParam);
	}

	@RequestMapping(value = "/bo({namespace},{name})/count", method = RequestMethod.GET)
	@ResponseBody
	public Long count(@PathVariable(value = "namespace") String namespace, @PathVariable(value = "name") String name,
			QueryParam queryParam) throws BusinessException {
		return this.businessObjectService().count(namespace, name, queryParam);
	}

	private BusinessObjectService businessObjectService() {
		return this.getBean(BusinessObjectService.class);
	}
}
