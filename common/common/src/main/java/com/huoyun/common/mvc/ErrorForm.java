package com.huoyun.common.mvc;

import java.util.HashMap;
import java.util.Map;

public class ErrorForm {

	private final Map<String, ErrorFormField> fields = new HashMap<>();

	public ErrorForm addField(String name, String errorMessage) {
		this.fields.put(name, new ErrorFormField(name, errorMessage));
		return this;
	}

	public boolean hasError(String name) {
		return this.fields.containsKey(name);
	}

	public ErrorFormField getError(String name) {
		return this.fields.get(name);
	}
	
	public String getErrorMessage(String name){
		return this.getError(name).getErrorMessage();
	}
	
	public void clear(){
		this.fields.clear();
	}
}
