package com.huoyun.common.query;

import org.springframework.data.domain.Pageable;

import com.huoyun.common.query.criteria.Criteria;

public interface Query {

	Query parse(String queryExpr);

	Query setFilter(Filter filter);

	Query addCriteria(Criteria criteria);

	Query addCriterias(Criteria... criterias);

	Filter getFilter();

	Query setSelect(String selects);

	boolean hasSelects();

	boolean isMatchSelect(String propName);

	Query addSelect(String select);

	Query setPageable(Pageable pageable);

	Pageable getPageable();
}
