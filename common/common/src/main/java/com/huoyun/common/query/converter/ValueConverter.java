package com.huoyun.common.query.converter;

import java.util.List;

import com.huoyun.common.exceptions.BusinessException;

public interface ValueConverter {

	Object converter(String value) throws BusinessException;

	List<Object> converterToList(String value) throws BusinessException;
}
