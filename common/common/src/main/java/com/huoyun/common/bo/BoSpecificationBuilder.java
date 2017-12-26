package com.huoyun.common.bo;

import com.huoyun.common.bo.impl.BoSpecificationImpl;
import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.query.Query;
import com.huoyun.common.query.criteria.CriteriaBuilder;
import com.huoyun.common.query.impl.QueryImpl;

public class BoSpecificationBuilder {

	private BusinessObjectMetadata boMeta;
	private Query query;

	public BoSpecificationBuilder(BusinessObjectMetadata boMeta, String queryExpr) throws BusinessException {
		this.boMeta = boMeta;
		CriteriaBuilder builder = new CriteriaBuilder(boMeta, queryExpr);
		this.query = new QueryImpl();
		this.query.addCriteria(builder.build());
	}

	public BoSpecification<?> build() throws BusinessException {
		return BoSpecificationImpl.createBoSpec(this.boMeta.getBoClass(), this.query);
	}
}
