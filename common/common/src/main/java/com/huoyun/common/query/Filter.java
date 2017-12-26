package com.huoyun.common.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.huoyun.common.query.criteria.Criteria;

public interface Filter {

	Filter addCriteria(Criteria criteria);

	Filter addCriterias(Criteria... criterias);

	Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder cb);
}
