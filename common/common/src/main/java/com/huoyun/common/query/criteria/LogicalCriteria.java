package com.huoyun.common.query.criteria;

public abstract class LogicalCriteria implements Criteria {

	private Criteria left;
	private Criteria right;

	public LogicalCriteria(Criteria left, Criteria right) {
		this.left = left;
		this.right = right;
	}

	public Criteria getLeft() {
		return left;
	}

	public Criteria getRight() {
		return right;
	}

}
