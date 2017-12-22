package com.huoyun.common.mvc;

public class ErrorFormField {

	private String name;
	private String errorMessage;

	public ErrorFormField(String name, String errorMessage) {
		this.name = name;
		this.errorMessage = errorMessage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
