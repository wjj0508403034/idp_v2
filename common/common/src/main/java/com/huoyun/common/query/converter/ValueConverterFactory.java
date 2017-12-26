package com.huoyun.common.query.converter;

import org.joda.time.DateTime;

import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.exceptions.ErrorCodes;
import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;

public class ValueConverterFactory {
	public static ValueConverter getValueConverter(BusinessObjectPropertyMetadata propMeta) throws BusinessException {
		if (propMeta.getRuntimeType() == String.class) {
			return new StringValueConverter(propMeta);
		}

		if (propMeta.getRuntimeType() == DateTime.class) {
			// return new DateTimeValueConverter(propMeta);
		}

		if (propMeta.getRuntimeType() == Double.class) {
			// return new DoubleValueConverter(propMeta);
		}

		if (propMeta.getRuntimeType() == Boolean.class || propMeta.getRuntimeType() == boolean.class) {
			return new BooleanValueConverter(propMeta);
		}

		if (propMeta.getRuntimeType() == Long.class) {
			return new LongValueConverter(propMeta);
		}

		// if (propMeta.getType() == PropertyType.BoLabel) {
		// return new BoValueConverter(propMeta);
		// }

		throw new BusinessException(ErrorCodes.Query_Expression_Invalid);
	}
}
