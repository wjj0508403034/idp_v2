package com.huoyun.common.query.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.huoyun.common.query.Filter;
import com.huoyun.common.query.criteria.And;
import com.huoyun.common.query.criteria.Criteria;

public class FilterImpl implements Filter {

	private Criteria criteria = null;

	@Override
	public Filter addCriteria(Criteria criteria) {
		if (this.criteria == null) {
			this.criteria = criteria;
			return this;
		}

		this.criteria = new And(this.criteria, criteria);
		return this;
	}

	@Override
	public Filter addCriterias(Criteria... criterias) {
		for (Criteria criteria : criterias) {
			this.addCriteria(criteria);
		}
		return this;
	}

	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (this.criteria != null) {
			return this.criteria.toPredicate(root, query, cb);
		}

		return null;
	}

}
