package com.huoyun.common.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.huoyun.common.bo.BoData;
import com.huoyun.common.bo.BoSpecification;
import com.huoyun.common.bo.BoSpecificationBuilder;
import com.huoyun.common.bo.BusinessObjectFacade;
import com.huoyun.common.bo.BusinessObjectService;
import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.exceptions.ErrorCodes;
import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectMetadataRepository;
import com.huoyun.common.metadata.annotation.BusinessObject;
import com.huoyun.common.service.AbstractBusinessService;

@Service
public class BusinessObjectServiceImpl extends AbstractBusinessService implements BusinessObjectService {

	@SuppressWarnings("rawtypes")
	@Override
	public Page<BoData> query(String namespace, String name, String queryExpr) throws BusinessException {
		BusinessObjectMetadata boMeta = this.boMetaRepository().getBoMeta(namespace, name);
		if (boMeta == null) {
			throw new BusinessException(ErrorCodes.Business_Object_Entity_Not_Exists);
		}

		BoSpecification boSpec = this.buildBoSpec(boMeta, queryExpr);

		Page<? extends BusinessObject> pageResult = this.boFacade().getBoRepository(boMeta.getBoClass()).query(boSpec, null);
		
		List<BoData> resultList = new ArrayList<>();
		for (BusinessObject bo : pageResult.getContent()) {
			//resultList.add(this.boMapper.converterTo(bo, boMeta));
		}

		return new PageImpl<BoData>(resultList, null,
				pageResult.getTotalElements());
	}

	private BusinessObjectMetadataRepository boMetaRepository() {
		return this.getBean(BusinessObjectMetadataRepository.class);
	}

	private BusinessObjectFacade boFacade() {
		return this.getBean(BusinessObjectFacade.class);
	}

	private BoSpecification<? extends BusinessObject> buildBoSpec(BusinessObjectMetadata boMeta, String queryExpr)
			throws BusinessException {
		BoSpecificationBuilder builder = new BoSpecificationBuilder(boMeta, queryExpr);
		return builder.build();
	}

}
