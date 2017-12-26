package com.huoyun.common.bo.impl;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.huoyun.common.bo.BoRepository;
import com.huoyun.common.bo.BusinessObjectFacade;
import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectMetadataRepository;
import com.huoyun.common.service.AbstractBusinessService;

@Service
public class BusinessObjectFacadeImpl extends AbstractBusinessService implements BusinessObjectFacade {

	@Override
	public BoRepository<?> getBoRepository(Class<?> boType) {
		BusinessObjectMetadata boMeta = this.boMetaRepo().getBoMeta(boType);
		if (boMeta == null) {
			throw new RuntimeException(String.format("Entity {0} not found", boType));
		}
		return BoRepositoryImpl.newRepo(this.entityManager(), boType);
	}

	private EntityManager entityManager() {
		return this.getBean(EntityManager.class);
	}

	private BusinessObjectMetadataRepository boMetaRepo() {
		return this.getBean(BusinessObjectMetadataRepository.class);
	}
}
