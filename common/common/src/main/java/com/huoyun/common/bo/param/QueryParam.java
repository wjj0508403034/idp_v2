package com.huoyun.common.bo.param;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class QueryParam {

	private String filter;
	private String select;
	private String expand;
	private String orderBy;
	private Integer pageIndex = 0;
	private Integer pageSize = 20;

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Pageable getPageable() {
		return new PageRequest(this.pageIndex, this.pageSize);
	}

}
