package com.huoyun.common.query.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NotEqual extends ComparableCriteria {

	public NotEqual(String name, Object value, Class<?> type) {
		super(name, value, type);
	}

	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.notEqual(this.getPathExpression(root), this.getValue());
	}

}
