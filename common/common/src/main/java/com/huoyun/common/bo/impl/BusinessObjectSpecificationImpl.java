package com.huoyun.common.bo.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.huoyun.common.bo.BusinessObjectSpecification;
import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.query.Filter;
import com.huoyun.common.query.Query;

public class BusinessObjectSpecificationImpl<T> implements BusinessObjectSpecification<T> {

	private Query query;
	private Class<T> boClass;

	public BusinessObjectSpecificationImpl(Query query) {
		this.query = query;
	}

	public static <T> BusinessObjectSpecificationImpl<T> createBoSpec(Class<T> klass, Query query)
			throws BusinessException {
		BusinessObjectSpecificationImpl<T> boSpec = new BusinessObjectSpecificationImpl<T>(query);
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
	public Class<T> getBoClass() {
		return this.boClass;
	}

	@Override
	public Query getQuery() {
		return this.query;
	}

}
