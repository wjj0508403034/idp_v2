package com.huoyun.common.bo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.metadata.annotation.BusinessObject;

public interface BoSpecification<T extends BusinessObject> {

	Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) throws BusinessException;

	Class<? extends BusinessObject> getBoClass();
}
