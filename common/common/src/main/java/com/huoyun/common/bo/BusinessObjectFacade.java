package com.huoyun.common.bo;

import com.huoyun.common.metadata.annotation.BusinessObject;

public interface BusinessObjectFacade {

	<T extends BusinessObject> BoRepository<T> getBoRepository(Class<T> boType);

}
