package com.huoyun.common.query.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LessThan extends ComparableCriteria {

	public LessThan(String name, Comparable<?> value) {
		super(name, value, Comparable.class);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.lessThan((Expression<Comparable>) this.getPathExpression(root), (Comparable) this.getValue());
	}
}
