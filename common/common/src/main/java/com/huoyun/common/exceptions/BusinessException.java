package com.huoyun.common.exceptions;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 4880304046817357510L;

	private String code;
	private String message;

	public BusinessException() {

	}

	public BusinessException(String code) {
		this.code = code;
	}

	public BusinessException(String code, String message) {
		this(code);
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
