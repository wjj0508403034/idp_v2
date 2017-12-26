package com.huoyun.common.metadata;

public interface BusinessObjectMetadataRepository {

	BusinessObjectMetadata getBoMeta(String boNamespace, String boName);

	BusinessObjectMetadata getBoMeta(String boName);

	BusinessObjectMetadata getBoMeta(Class<?> boType);

}
