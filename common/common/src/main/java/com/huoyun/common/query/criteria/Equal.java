package com.huoyun.common.query.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Equal extends ComparableCriteria {

	public Equal(String name, Object value, Class<?> type) {
		super(name, value, type);
	}

	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.equal(this.getPathExpression(root), this.getValue());
	}
}
