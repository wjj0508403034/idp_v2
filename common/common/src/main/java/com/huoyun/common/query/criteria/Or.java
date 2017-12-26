package com.huoyun.common.query.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Or extends LogicalCriteria {

	public Or(Criteria left, Criteria right) {
		super(left, right);
	}

	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate leftPredicate = this.getLeft().toPredicate(root, query, cb);
		Predicate rightPredicate = this.getRight().toPredicate(root, query, cb);
		return cb.or(leftPredicate, rightPredicate);
	}

}
