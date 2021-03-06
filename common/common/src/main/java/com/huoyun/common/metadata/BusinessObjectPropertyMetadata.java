package com.huoyun.common.metadata;

public interface BusinessObjectPropertyMetadata {

	String getIdentityName();

	boolean isSearchable();

	boolean isReadonly();

	boolean isMandatory();

	String getName();

	String getNamespace();

	String getLabel();

	Class<?> getRuntimeType();

	boolean isExposed();

	BusinessObjectMetadata getBoMeta();

	boolean isIdField();

}
