package com.huoyun.common.query.impl;

import org.apache.commons.lang3.StringUtils;

import com.huoyun.common.query.Select;

public class SelectImpl implements Select {

	private String value;

	public SelectImpl(String select) {
		this.value = select;
	}

	@Override
	public boolean isMatch(String propName) {
		return StringUtils.equals(propName, this.value);
	}

}
