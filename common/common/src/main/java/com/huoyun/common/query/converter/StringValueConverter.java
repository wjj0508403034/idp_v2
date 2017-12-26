package com.huoyun.common.query.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.exceptions.ErrorCodes;
import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;

public class StringValueConverter extends AbstractValueConverter {

	public StringValueConverter(BusinessObjectPropertyMetadata propMeta) {
		super(propMeta);

	}

	@Override
	public Object converter(String value) throws BusinessException {
		if (StringUtils.equals(value, "NULL")) {
			return null;
		}

		if (value.startsWith("'") && value.endsWith("'")) {
			return StringUtils.substring(value, 1, value.length() - 1);
		}

		throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
	}

	@Override
	public List<Object> converterToList(String value) throws BusinessException {
		List<Object> values = new ArrayList<>();
		for (String item : value.split(",")) {
			values.add(this.converter(item));
		}
		return values;
	}

}
