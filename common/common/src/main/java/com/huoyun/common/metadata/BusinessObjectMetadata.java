package com.huoyun.common.metadata;

import java.util.List;

import com.huoyun.common.metadata.annotation.BusinessObject;

public interface BusinessObjectMetadata {

	String getName();

	String getNamespace();

	boolean isExposed();

	String getLabel();

	List<BusinessObjectPropertyMetadata> getPropertyMetadatas();

	BusinessObjectPropertyMetadata getPropertyMetadata(String propertyNamespace, String propertyName);

	BusinessObjectPropertyMetadata getPropertyMetadata(String propertyName);

	String getIdentityName();

	Class<? extends BusinessObject> getBoClass();

}
