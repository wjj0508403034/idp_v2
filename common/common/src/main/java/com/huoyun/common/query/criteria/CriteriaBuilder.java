package com.huoyun.common.query.criteria;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.exceptions.ErrorCodes;
import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;
import com.huoyun.common.query.converter.ValueConverter;
import com.huoyun.common.query.converter.ValueConverterFactory;
import com.huoyun.common.query.parse.Cursor;
import com.huoyun.common.query.parse.QueryParser;
import com.huoyun.common.query.token.ListToken;
import com.huoyun.common.query.token.StringToken;
import com.huoyun.common.query.token.Token;

public class CriteriaBuilder {

	private final static String Equal = "eq";
	private final static String NotEqual = "ne";
	private final static String GreaterThan = "gt";
	private final static String GreaterThanAndEqual = "ge";
	private final static String LessThan = "lt";
	private final static String LessThanAndEqual = "le";
	private final static String Between = "between";

	private final static String And = "and";
	private final static String Or = "or";
	private final static String Like = "like";
	private final static String In = "in";

	private final static Map<String, Class<?>> CriteriaMap = new HashMap<>();

	static {
		CriteriaMap.put(Equal, Equal.class);
		CriteriaMap.put(NotEqual, NotEqual.class);
		CriteriaMap.put(GreaterThan, GreaterThan.class);
		CriteriaMap.put(GreaterThanAndEqual, GreaterThanAndEqual.class);
		CriteriaMap.put(LessThan, LessThan.class);
		CriteriaMap.put(LessThanAndEqual, LessThanAndEqual.class);
		// CriteriaMap.put(Between, Between.class);

		CriteriaMap.put(Like, Like.class);
		// CriteriaMap.put(In, In.class);

		CriteriaMap.put(And, And.class);
		CriteriaMap.put(Or, Or.class);
	}

	private BusinessObjectMetadata boMeta;
	private String value;

	public CriteriaBuilder(BusinessObjectMetadata boMeta, String value) {
		this.boMeta = boMeta;
		this.value = value;
	}

	public Criteria build() throws BusinessException {
		List<Token> tokens = QueryParser.parse(this.value);
		return this.parse(this.boMeta, tokens, new Cursor());
	}

	private Criteria parse(BusinessObjectMetadata boMeta, List<Token> tokens, Cursor cursor) throws BusinessException {

		if (tokens.size() - cursor.getValue() <= 0) {
			throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
		}

		if (tokens.size() - cursor.getValue() == 1) {
			if (tokens.get(cursor.getValue()) instanceof ListToken) {
				return this.parse(boMeta, this.getListTokens(tokens.get(cursor.getValue())), new Cursor());
			}

			throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
		}

		if (tokens.size() - cursor.getValue() == 2) {
			throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
		}

		Token opToken = tokens.get(cursor.getValue() + 1);
		Class<Criteria> criteriaClass = this.getCriteriaClass(opToken.getValue());
		Token leftToken = tokens.get(cursor.getValue());

		if (ComparableCriteria.class.isAssignableFrom(criteriaClass)) {
			Token rightToken = tokens.get(cursor.getValue() + 2);
			Criteria criteria = this.newComparableCriteria(boMeta, criteriaClass, leftToken, rightToken);
			cursor.move(3);
			if (cursor.getValue() >= tokens.size()) {
				return criteria;
			}

			Token nextToken = tokens.get(cursor.getValue());
			Class<Criteria> nextCriteriaClass = this.getCriteriaClass(nextToken.getValue());
			if (!LogicalCriteria.class.isAssignableFrom(nextCriteriaClass)) {
				throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
			}

			cursor.move(1);
			Criteria rightCriteria = this.parse(boMeta, tokens, cursor);
			Criteria nextCriteria = this.newLogicalCriteria(boMeta, nextCriteriaClass, criteria, rightCriteria);
			return nextCriteria;

		}

		if (LogicalCriteria.class.isAssignableFrom(criteriaClass)) {
			if (!(leftToken instanceof ListToken)) {
				throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
			}

			Criteria leftCriteria = this.parse(boMeta, this.getListTokens(leftToken), new Cursor());
			cursor.move(2);

			Criteria rightCriteria = this.parse(boMeta, tokens, cursor);
			Criteria nextCriteria = this.newLogicalCriteria(boMeta, criteriaClass, leftCriteria, rightCriteria);
			return nextCriteria;
		}

		throw new BusinessException(ErrorCodes.Query_Expression_Invalid);

	}

	private Criteria newComparableCriteria(BusinessObjectMetadata boMeta, Class<Criteria> criteriaClass,
			Token propNameToken, Token propValueToken) throws BusinessException {
		BusinessObjectPropertyMetadata propMeta = this.getPropMeta(boMeta, propNameToken);
		Object propValue = null;
		// if (!CollectionCriteria.class.isAssignableFrom(criteriaClass)) {
		propValue = this.getValue(propMeta, propValueToken);
		// } else {
		// propValue = this.getMultiValues(propMeta, propValueToken);
		// }

		try {
			Constructor<Criteria> constructor = criteriaClass.getConstructor(String.class, Object.class, Class.class);
			return constructor.newInstance(propMeta.getName(), propValue, propMeta.getRuntimeType());
		} catch (Exception e) {
			throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
		}
	}

	private Object getValue(BusinessObjectPropertyMetadata propMeta, Token valueToken) throws BusinessException {
		ValueConverter valueConverter = ValueConverterFactory.getValueConverter(propMeta);
		if (valueConverter != null && valueToken instanceof StringToken) {
			return valueConverter.converter(valueToken.getValue());
		}

		throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
	}

	private BusinessObjectPropertyMetadata getPropMeta(BusinessObjectMetadata boMeta, Token propToken)
			throws BusinessException {
		if (propToken instanceof StringToken) {
			String propName = propToken.getValue();
			BusinessObjectPropertyMetadata propMeta = boMeta.getPropertyMetadata(propName);
			if (propMeta != null) {
				return propMeta;
			}
		}

		throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
	}

	private List<Token> getListTokens(Token token) {
		return ((ListToken) token).getTokens();
	}

	private Criteria newLogicalCriteria(BusinessObjectMetadata boMeta, Class<Criteria> criteriaClass,
			Criteria leftCriteria, Criteria rightCriteria) throws BusinessException {

		try {
			Constructor<Criteria> constructor = criteriaClass.getConstructor(Criteria.class, Criteria.class);
			return constructor.newInstance(leftCriteria, rightCriteria);
		} catch (Exception e) {
			throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends Criteria> Class<T> getCriteriaClass(String op) throws BusinessException {
		if (CriteriaMap.containsKey(op.toLowerCase())) {
			return (Class<T>) CriteriaMap.get(op.toLowerCase());
		}

		throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
	}
}
