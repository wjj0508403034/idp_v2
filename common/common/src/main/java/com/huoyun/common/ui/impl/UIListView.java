package com.huoyun.common.ui.impl;

import java.util.ArrayList;
import java.util.List;

import com.huoyun.common.ui.xml.Column;
import com.huoyun.common.ui.xml.ListView;

public class UIListView {

	private List<String> properties = new ArrayList<>();
	private String orderby;
	private String sort;

	public UIListView() {

	}

	public UIListView(ListView listView) {
		for (Column column : listView.getColumns()) {
			this.properties.add(column.getRef());
		}

		this.sort = listView.getSortProperty();
		this.orderby = listView.getOrderby();
	}

	public List<String> getProperties() {
		return properties;
	}

	public void setProperties(List<String> properties) {
		this.properties = properties;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}