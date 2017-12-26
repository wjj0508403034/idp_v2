package com.huoyun.common.query.token;

public abstract class AbstractToken implements Token{

	private String value;
	
	public AbstractToken(String value){
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
