package com.huoyun.common.bo;

public interface BusinessObjectFacade {


	BoRepository<?> getBoRepository(Class<?> boType);

}
