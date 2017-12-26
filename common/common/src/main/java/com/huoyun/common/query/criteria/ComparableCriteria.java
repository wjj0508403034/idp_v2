package com.huoyun.common.query.criteria;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

public abstract class ComparableCriteria implements Criteria {

	private String name;
	private Object value;
	private Class<?> type;

	public ComparableCriteria(String name, Object value, Class<?> type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public Class<?> getType() {
		return type;
	}

	protected Expression<?> getPathExpression(Root<?> root) {
		return root.get(this.getName()).as(this.getType());
	}

}
