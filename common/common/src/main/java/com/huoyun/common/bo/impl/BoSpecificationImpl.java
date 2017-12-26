package com.huoyun.common.bo.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.huoyun.common.bo.BoSpecification;
import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.metadata.annotation.BusinessObject;
import com.huoyun.common.query.Filter;
import com.huoyun.common.query.Query;

public class BoSpecificationImpl<T extends BusinessObject> implements BoSpecification<T> {

	private Query query;
	private Class<T> boClass;

	public BoSpecificationImpl(Query query) {
		this.query = query;
	}

	public static <T extends BusinessObject> BoSpecificationImpl<T> createBoSpec(Class<T> klass, Query query)
			throws BusinessException {
		BoSpecificationImpl<T> boSpec = new BoSpecificationImpl<T>(query);
		boSpec.boClass = klass;
		return boSpec;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) throws BusinessException {
		Filter filter = this.query.getFilter();
		if (filter != null) {
			Predicate predicate = filter.toPredicate(root, query, cb);
			if (predicate != null) {
				query = query.where(predicate);
			}
		}

		return query.getRestriction();
	}

	@Override
	public Class<? extends BusinessObject> getBoClass(){
		return this.boClass;
	}

}
