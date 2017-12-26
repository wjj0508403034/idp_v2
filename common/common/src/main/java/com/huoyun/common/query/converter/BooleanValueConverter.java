package com.huoyun.common.query.converter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.exceptions.ErrorCodes;
import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;

public class BooleanValueConverter extends AbstractValueConverter {

	public BooleanValueConverter(BusinessObjectPropertyMetadata propMeta) {
		super(propMeta);

	}

	private final static String TrueValue = "true";
	private final static String FalseValue = "false";

	@Override
	public Object converter(String value) throws BusinessException {
		if (StringUtils.equalsIgnoreCase(FalseValue, value)) {
			return false;
		}

		if (StringUtils.equalsIgnoreCase(TrueValue, value)) {
			return true;
		}
		throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
	}

	@Override
	public List<Object> converterToList(String value) throws BusinessException {
		return null;
	}

}
