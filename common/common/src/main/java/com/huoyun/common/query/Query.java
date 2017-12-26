package com.huoyun.common.query;

import com.huoyun.common.query.criteria.Criteria;

public interface Query {

	Query parse(String queryExpr);
	
	Query setFilter(Filter filter);
	
	Query addCriteria(Criteria criteria);

	Query addCriterias(Criteria... criterias);

	Filter getFilter();
}
