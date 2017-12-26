package com.huoyun.common.bo;

public interface BusinessObjectFacade {


	BusinessObjectRepository<?> getBoRepository(Class<?> boType);

}
