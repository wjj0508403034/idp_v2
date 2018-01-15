package com.huoyun.common.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import com.huoyun.common.bo.BoData;
import com.huoyun.common.bo.BusinessObjectSpecification;
import com.huoyun.common.bo.param.QueryParam;
import com.huoyun.common.bo.BusinessObjectFacade;
import com.huoyun.common.bo.BusinessObjectMapper;
import com.huoyun.common.bo.BusinessObjectRepository;
import com.huoyun.common.bo.BusinessObjectService;
import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.exceptions.ErrorCodes;
import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectMetadataRepository;
import com.huoyun.common.query.Query;
import com.huoyun.common.query.criteria.CriteriaBuilder;
import com.huoyun.common.query.impl.QueryImpl;
import com.huoyun.common.service.AbstractBusinessService;

public class BusinessObjectServiceImpl extends AbstractBusinessService
		implements BusinessObjectService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page<BoData> query(String namespace, String name,
			QueryParam queryParam) throws BusinessException {
		BusinessObjectMetadata boMeta = this.getBoMeta(namespace, name);

		Query query = this.buildQuery(boMeta, queryParam);
		BusinessObjectSpecification boSpec = this.buildBoSpec(boMeta, query);

		Page<?> pageResult = this.boRepository(boMeta.getBoClass()).query(
				boSpec);

		return this.boMapper().mapper(pageResult, boMeta, query);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Long count(String namespace, String name, QueryParam queryParam)
			throws BusinessException {
		BusinessObjectMetadata boMeta = this.getBoMeta(namespace, name);

		Query query = this.buildQuery(boMeta, queryParam);
		BusinessObjectSpecification boSpec = this.buildBoSpec(boMeta, query);

		return this.boRepository(boMeta.getBoClass()).count(boSpec);
	}

	@Override
	public BoData load(String namespace, String name, Long id)
			throws BusinessException {
		BusinessObjectMetadata boMeta = this.getBoMeta(namespace, name);

		Object bo = this.loadBoById(boMeta, id);

		return this.boMapper().mapper(bo, boMeta);
	}

	@Transactional
	@Override
	public BoData createBo(String namespace, String name, BoData boData)
			throws BusinessException {
		BusinessObjectMetadata boMeta = this.getBoMeta(namespace, name);
		Object bo = this.boFacade().newBo(boMeta.getBoClass());
		this.boMapper().merge(bo, boData, boMeta);

		BusinessObjectRepository<?> boRepo = this.boRepository(boMeta
				.getBoClass());
		boRepo.create(bo);

		return this.boMapper().mapper(bo, boMeta);
	}

	@Transactional
	@Override
	public BoData updateBo(String namespace, String name, Long id, BoData boData)
			throws BusinessException {
		BusinessObjectMetadata boMeta = this.getBoMeta(namespace, name);
		BusinessObjectRepository<?> boRepo = this.boRepository(boMeta
				.getBoClass());

		Object bo = this.loadBoById(boMeta, id);
		this.boMapper().merge(bo, boData, boMeta);
		boRepo.update(bo);

		return this.boMapper().mapper(bo, boMeta);
	}

	@Transactional
	@Override
	public void delete(String namespace, String name, Long id)
			throws BusinessException {
		BusinessObjectMetadata boMeta = this.getBoMeta(namespace, name);
		Object bo = this.loadBoById(boMeta, id);
		this.boRepository(boMeta.getBoClass()).delete(bo);
	}

	private Object loadBoById(BusinessObjectMetadata boMeta, Long id)
			throws BusinessException {
		Object bo = this.boRepository(boMeta.getBoClass()).load(id);
		if (bo == null) {
			throw new BusinessException(ErrorCodes.Business_Object_Not_Found);
		}
		return bo;
	}

	private BusinessObjectMetadata getBoMeta(String namespace, String name)
			throws BusinessException {
		BusinessObjectMetadata boMeta = this.boMetaRepository().getBoMeta(
				namespace, name);
		if (boMeta == null) {
			throw new BusinessException(
					ErrorCodes.Business_Object_Entity_Not_Exists);
		}
		return boMeta;
	}

	private BusinessObjectMetadataRepository boMetaRepository() {
		return this.getBean(BusinessObjectMetadataRepository.class);
	}

	private BusinessObjectFacade boFacade() {
		return this.getBean(BusinessObjectFacade.class);
	}

	private BusinessObjectMapper boMapper() {
		return this.getBean(BusinessObjectMapper.class);
	}

	private BusinessObjectRepository<?> boRepository(Class<?> boClass) {
		return this.boFacade().getBoRepository(boClass);
	}

	private BusinessObjectSpecification<?> buildBoSpec(
			BusinessObjectMetadata boMeta, Query query)
			throws BusinessException {
		return BusinessObjectSpecificationImpl.createBoSpec(
				boMeta.getBoClass(), query);
	}

	private Query buildQuery(BusinessObjectMetadata boMeta,
			QueryParam queryParam) throws BusinessException {
		Query query = new QueryImpl();
		CriteriaBuilder builder = this.createCriteriaBuilder(boMeta,
				queryParam.getFilter());
		if (builder != null) {
			query.addCriteria(builder.build());
		}

		query.setSelect(queryParam.getSelect()).setPageable(
				queryParam.getPageable());
		return query;
	}

	private CriteriaBuilder createCriteriaBuilder(
			BusinessObjectMetadata boMeta, String filter) {
		if (StringUtils.isEmpty(filter)) {
			return null;
		}

		return new CriteriaBuilder(boMeta, filter);
	}

}
