package com.huoyun.common.metadata;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huoyun.common.service.AbstractBusinessService;

public abstract class MetadataController extends AbstractBusinessService {

	@RequestMapping(value = "/metadata", method = RequestMethod.GET)
	@ResponseBody
	public BusinessObjectMetadata getBoMetadata(@RequestParam("boNamespace") String boNamespace,
			@RequestParam("boName") String boName) {
		return this.boMetaRepository().getBoMeta(boNamespace, boName);
	}

	private BusinessObjectMetadataRepository boMetaRepository() {
		return this.getBean(BusinessObjectMetadataRepository.class);
	}
}
