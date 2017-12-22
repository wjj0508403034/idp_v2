package com.huoyun.common.metadata;

import java.util.List;

public interface BusinessObjectMetadata {

	String getName();

	String getNamespace();

	boolean isExposed();

	String getLabel();

	List<BusinessObjectPropertyMetadata> getPropertyMetadatas();

	BusinessObjectPropertyMetadata getPropertyMetadata(String propertyNamespace, String propertyName);

	BusinessObjectPropertyMetadata getPropertyMetadata(String propertyName);

	String getIdentityName();

}
