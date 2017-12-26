package com.huoyun.common.query.token;

import java.util.ArrayList;
import java.util.List;

public class ListToken extends AbstractToken {

	private final List<Token> tokens = new ArrayList<>();

	public ListToken(String value) {
		super(value);
	}

	public List<Token> getTokens() {
		return this.tokens;
	}

}
