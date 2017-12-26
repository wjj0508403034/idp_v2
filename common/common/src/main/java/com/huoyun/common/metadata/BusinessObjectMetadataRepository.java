package com.huoyun.common.metadata;

import com.huoyun.common.metadata.annotation.BusinessObject;

public interface BusinessObjectMetadataRepository {

	BusinessObjectMetadata getBoMeta(String boNamespace, String boName);

	BusinessObjectMetadata getBoMeta(String boName);

	<T extends BusinessObject> BusinessObjectMetadata getBoMeta(Class<T> boType);

}
