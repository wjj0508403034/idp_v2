package com.huoyun.common.query.parse;

public abstract class AbstractParser implements Parser {
	private String value;

	public AbstractParser(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}
