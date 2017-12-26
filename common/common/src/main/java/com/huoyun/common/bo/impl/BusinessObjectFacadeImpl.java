package com.huoyun.common.bo.impl;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huoyun.common.bo.BusinessObjectRepository;
import com.huoyun.common.bo.BusinessObjectFacade;
import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectMetadataRepository;
import com.huoyun.common.service.AbstractBusinessService;

@Service
public class BusinessObjectFacadeImpl extends AbstractBusinessService implements BusinessObjectFacade {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessObjectFacadeImpl.class);

	@Override
	public BusinessObjectRepository<?> getBoRepository(Class<?> boType) {
		BusinessObjectMetadata boMeta = this.boMetaRepo().getBoMeta(boType);
		if (boMeta == null) {
			throw new RuntimeException(String.format("Entity {0} not found", boType));
		}
		return BusinessObjectRepositoryImpl.newRepo(this.entityManager(), boType);
	}

	@Override
	public Object newBo(Class<?> boType) {
		Object bo = null;
		try {
			bo = boType.getConstructor().newInstance();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return bo;
	}

	private EntityManager entityManager() {
		return this.getBean(EntityManager.class);
	}

	private BusinessObjectMetadataRepository boMetaRepo() {
		return this.getBean(BusinessObjectMetadataRepository.class);
	}

}
