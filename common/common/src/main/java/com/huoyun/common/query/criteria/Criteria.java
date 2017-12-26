package com.huoyun.common.query.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Criteria {

	Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder cb);
}
