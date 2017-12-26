package com.huoyun.common.query.converter;

import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;

public abstract class AbstractValueConverter implements ValueConverter {

	private final BusinessObjectPropertyMetadata propMeta;

	public AbstractValueConverter(BusinessObjectPropertyMetadata propMeta) {
		this.propMeta = propMeta;
	}

	protected BusinessObjectPropertyMetadata getPropMeta() {
		return this.propMeta;
	}
}
