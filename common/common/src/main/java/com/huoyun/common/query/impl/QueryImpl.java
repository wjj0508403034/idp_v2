package com.huoyun.common.query.impl;

import com.huoyun.common.query.Filter;
import com.huoyun.common.query.Query;
import com.huoyun.common.query.criteria.Criteria;

public class QueryImpl implements Query {

	private Filter filter;

	@Override
	public Query parse(String queryExpr) {
		return null;
	}
	
	@Override
	public Filter getFilter(){
		return this.filter;
	}

	@Override
	public Query setFilter(Filter filter) {
		this.filter = filter;
		return this;
	}

	@Override
	public Query addCriteria(Criteria criteria) {
		return this.addCriterias(criteria);
	}

	@Override
	public Query addCriterias(Criteria... criterias) {
		if (this.filter == null) {
			this.filter = new FilterImpl();
		}
		this.filter.addCriterias(criterias);
		return this;
	}

}
