package com.huoyun.common.query.parse;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.huoyun.common.query.token.ListToken;
import com.huoyun.common.query.token.StringToken;
import com.huoyun.common.query.token.Token;

public class QueryParser extends AbstractParser {

	private static final char LeftBracket = '(';
	private static final char RightBracket = ')';
	private static final char SingleQuotes = '\'';
	private static final char WhiteSpace = ' ';

	private final static List<String> CollectionFunctionList = new ArrayList<>();
	private final List<Token> tokens = new ArrayList<>();

	static {
		CollectionFunctionList.add("in");
		CollectionFunctionList.add("between");
	}

	public QueryParser(String value) {
		super(value);

		this.parse(value, new Cursor(), tokens);
	}

	public static List<Token> parse(String value) {
		QueryParser parser = new QueryParser(value);
		return parser.tokens;
	}

	private void parse(String value, Cursor cursor, List<Token> tokens) {
		int quotesCounter = 0;

		while (cursor.getValue() < value.length()) {
			char currentChar = value.charAt(cursor.getValue());
			if (currentChar == SingleQuotes) {
				quotesCounter++;
			} else if (currentChar == LeftBracket) {
				if (quotesCounter % 2 == 0) {
					if (cursor.getStart() < cursor.getValue()) {
						this.pushStringToken(value, cursor, tokens);
					}

					parseBracket(value, cursor, tokens);
					continue;
				}
			} else if (currentChar == WhiteSpace) {
				if (quotesCounter % 2 == 0) {
					if (cursor.getStart() < cursor.getValue()) {
						this.pushStringToken(value, cursor, tokens);
					}

					if (value.charAt(cursor.getValue()) == WhiteSpace) {
						this.moveToUnWhitespaceChar(value, cursor);
					}

					parse(value, cursor, tokens);
				}
			}

			cursor.move(1);
		}

		if (cursor.getStart() < cursor.getValue() && cursor.getValue() <= value.length()) {
			this.pushStringToken(value, cursor, tokens);
		}
	}

	private void moveToUnWhitespaceChar(String expr, Cursor cursor) {
		while (cursor.getValue() < expr.length()) {
			cursor.move(1);
			if (expr.charAt(cursor.getValue()) != WhiteSpace) {
				break;
			}
		}

		cursor.resetStart();
	}

	private void parseBracket(String expr, Cursor cursor, List<Token> tokens) {
		int start = cursor.getValue();
		int end = -1;
		int quotesCounter = 0;
		int leftBracketCounter = 0;
		int rightBracketCounter = 0;
		while (cursor.getValue() < expr.length()) {
			char currentChar = expr.charAt(cursor.getValue());
			cursor.move(1);
			if (currentChar == SingleQuotes) {
				quotesCounter++;
			} else if (currentChar == RightBracket) {
				if (quotesCounter % 2 == 0) {
					rightBracketCounter++;
					if (rightBracketCounter == leftBracketCounter) {
						end = cursor.getValue();
						break;
					}
				}
			} else if (currentChar == LeftBracket) {
				if (quotesCounter % 2 == 0) {
					leftBracketCounter++;
				}
			}
		}

		if (end != -1 && cursor.getValue() > start) {
			String subExpr = StringUtils.substring(expr, start, cursor.getValue());
			String bracketContent = StringUtils.substring(subExpr, 1, subExpr.length() - 1);
			int startIndex = cursor.getStart();
			cursor.resetStart();
			if (tokens.size() > 0) {
				char lastChar = expr.charAt(startIndex - 1);
				Token lastToken = tokens.get(tokens.size() - 1);
				if (lastToken instanceof StringToken) {
					if (this.isCollection(lastToken.getValue())) {
						this.putCollectionStringToken(lastChar, tokens, bracketContent);
						return;
					}

					if (this.isFunction(lastChar, lastToken.getValue())) {
						this.putFunctionStringToken(lastToken, subExpr);
						return;
					}

				} else {
					throw new RuntimeException("Parse token failed");
				}
			}

			ListToken listToken = new ListToken(subExpr);
			tokens.add(listToken);
			this.parse(bracketContent, new Cursor(), listToken.getTokens());
			return;
		}

		throw new RuntimeException("Parse token failed");

	}

	private boolean isCollection(String expr) {
		for (String item : CollectionFunctionList) {
			if (StringUtils.equalsIgnoreCase(item, expr)) {
				return true;
			}
		}

		return false;
	}

	private void putCollectionStringToken(char lastChar, List<Token> tokens, String expr) {
		if (lastChar != WhiteSpace) {
			throw new RuntimeException("Parse token failed");
		}

		tokens.add(new StringToken(expr));
	}

	private boolean isFunction(char lastChar, String expr) {
		if (lastChar == WhiteSpace) {
			return false;
		}

		return FunctionUtils.isFunc(expr);
	}

	private void pushStringToken(String expr, Cursor cursor, List<Token> tokens) {
		String subExpr = StringUtils.substring(expr, cursor.getStart(), cursor.getValue());
		tokens.add(new StringToken(subExpr));
		cursor.resetStart();
	}

	private void putFunctionStringToken(Token lastToken, String expr) {
		if (expr.length() != 2) {
			throw new RuntimeException("Parse token failed");
		}

		lastToken.setValue(lastToken.getValue() + expr);
		return;

	}

}
