package com.huoyun.common.query.converter;

import java.util.ArrayList;
import java.util.List;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.exceptions.ErrorCodes;
import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;

public class LongValueConverter extends AbstractValueConverter {
	public LongValueConverter(BusinessObjectPropertyMetadata propMeta) {
		super(propMeta);

	}

	@Override
	public Object converter(String value) throws BusinessException {

		try {
			return Long.parseLong(value);
		} catch (Exception ex) {
			throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
		}

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
