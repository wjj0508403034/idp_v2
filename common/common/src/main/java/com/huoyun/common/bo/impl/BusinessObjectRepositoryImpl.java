package com.huoyun.common.bo.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;

import com.huoyun.common.bo.BusinessObjectRepository;
import com.huoyun.common.bo.BusinessObjectSpecification;
import com.huoyun.common.exceptions.BusinessException;

public class BusinessObjectRepositoryImpl<T> implements BusinessObjectRepository<T> {

	private EntityManager entityManager;
	private Class<T> boClass;

	private BusinessObjectRepositoryImpl(EntityManager entityManager, Class<T> boClass) {
		this.entityManager = entityManager;
		this.boClass = boClass;
	}

	public static <T> BusinessObjectRepository<T> newRepo(EntityManager entityManager, Class<T> boClass) {
		BusinessObjectRepositoryImpl<T> repo = new BusinessObjectRepositoryImpl<T>(entityManager, boClass);
		return repo;
	}

	@Override
	public Page<T> query(BusinessObjectSpecification<T> spec) throws BusinessException {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(this.boClass);

		Root<T> root = applySpecificationToCriteria(spec, criteriaQuery);
		criteriaQuery.select(root);

		TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
		Page<T> page = readPage(query, spec.getQuery().getPageable(), spec);

		return page;
	}

	@Override
	public Long count(BusinessObjectSpecification<T> spec) throws BusinessException {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<T> root = applySpecificationToCriteria(spec, criteriaQuery);

		if (criteriaQuery.isDistinct()) {
			criteriaQuery.select(builder.countDistinct(root));
		} else {
			criteriaQuery.select(builder.count(root));
		}

		TypedQuery<Long> query = this.entityManager.createQuery(criteriaQuery);

		return executeCountQuery(query);
	}

	@Override
	public void create(Object bo) {
		this.entityManager.persist(bo);
	}

	@Modifying
	@Override
	public void update(Object bo) {
		this.entityManager.merge(bo);
	}

	@Modifying
	@Override
	public void delete(Object bo) {
		this.entityManager.remove(bo);
	}

	@Override
	public T load(Long id) {
		return this.entityManager.find(this.boClass, id);
	}

	private <S> Root<T> applySpecificationToCriteria(BusinessObjectSpecification<T> spec, CriteriaQuery<S> query)
			throws BusinessException {

		Root<T> root = (Root<T>) query.from(this.boClass);

		if (spec == null) {
			return root;
		}

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		Predicate predicate = spec.toPredicate(root, query, builder);

		if (predicate != null) {
			query.where(predicate);
		}

		return root;
	}

	private Page<T> readPage(TypedQuery<T> query, Pageable pageable, BusinessObjectSpecification<T> spec)
			throws BusinessException {

		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());

		Long total = executeCountQuery(getCountQuery(spec));
		List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T> emptyList();

		return new PageImpl<T>(content, pageable, total);
	}

	private TypedQuery<Long> getCountQuery(BusinessObjectSpecification<T> spec) throws BusinessException {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);

		Root<T> root = applySpecificationToCriteria(spec, query);

		if (query.isDistinct()) {
			query.select(builder.countDistinct(root));
		} else {
			query.select(builder.count(root));
		}

		return this.entityManager.createQuery(query);
	}

	private static Long executeCountQuery(TypedQuery<Long> query) {

		List<Long> totals = query.getResultList();
		Long total = 0L;

		for (Long element : totals) {
			total += element == null ? 0 : element;
		}

		return total;
	}

}
