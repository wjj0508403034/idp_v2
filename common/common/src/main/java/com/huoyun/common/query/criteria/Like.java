package com.huoyun.common.query.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Like extends ComparableCriteria {

	public Like(String name, String value) {
		super(name, value, String.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String pattern = (String) this.getValue();
		pattern = "%" + pattern + "%";
		return cb.like((Expression<String>) this.getPathExpression(root), pattern);
	}

}
