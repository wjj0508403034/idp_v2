package com.huoyun.common.query.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;

import com.huoyun.common.query.Filter;
import com.huoyun.common.query.Query;
import com.huoyun.common.query.Select;
import com.huoyun.common.query.criteria.Criteria;

public class QueryImpl implements Query {

	private Filter filter;
	private List<Select> selects;
	private Pageable pageable;

	@Override
	public Query parse(String queryExpr) {
		return null;
	}

	@Override
	public Filter getFilter() {
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

	@Override
	public Query setSelect(String select) {
		this.selects = new ArrayList<>();
		if (!StringUtils.isEmpty(select)) {
			String[] selects = select.split(",");
			for (String selectItem : selects) {
				this.addSelect(selectItem);
			}
		}

		return this;
	}

	@Override
	public Query addSelect(String select) {
		if (!StringUtils.isEmpty(select)) {
			if (this.selects == null) {
				this.selects = new ArrayList<>();
			}
			this.selects.add(new SelectImpl(select));
		}

		return this;
	}

	@Override
	public boolean hasSelects() {
		return this.selects != null && this.selects.size() != 0;
	}

	@Override
	public boolean isMatchSelect(String propName) {
		if (this.hasSelects()) {
			for (Select select : this.selects) {
				if (select.isMatch(propName)) {
					return true;
				}
			}
		}

		return false;
	}
	
	@Override
	public Query setPageable(Pageable pageable){
		this.pageable = pageable;
		return this;
	}
	
	@Override
	public Pageable getPageable(){
		return this.pageable;
	}

}
