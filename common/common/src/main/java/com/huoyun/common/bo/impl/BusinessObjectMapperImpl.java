package com.huoyun.common.bo.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huoyun.common.bo.BoData;
import com.huoyun.common.bo.BusinessObjectMapper;
import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.exceptions.ErrorCodes;
import com.huoyun.common.exceptions.LocatableBusinessException;
import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;
import com.huoyun.common.query.Query;

public class BusinessObjectMapperImpl implements BusinessObjectMapper {

	@Override
	public BoData mapper(Object bo, BusinessObjectMetadata boMeta) throws BusinessException {
		return this.mapper(bo, boMeta, null);
	}

	@Override
	public BoData mapper(Object value, BusinessObjectMetadata boMeta, Query query) throws BusinessException {
		if (value == null) {
			return null;
		}

		BoData boData = new BoData();

		for (BusinessObjectPropertyMetadata propMeta : boMeta.getPropertyMetadatas()) {
			if (this.isPropExposed(propMeta, query)) {
				boData.put(propMeta.getName(), this.getPropValue(propMeta, value));
			}
		}

		return boData;
	}

	@Override
	public void merge(Object bo, BoData boData, BusinessObjectMetadata boMeta) throws BusinessException {
		if (boData != null) {
			for (BusinessObjectPropertyMetadata propMeta : boMeta.getPropertyMetadatas()) {
				if (this.isPropCanMerge(propMeta)) {
					if (boData.containsKey(propMeta.getName())) {
						Object propValue = boData.get(propMeta.getName());
						this.setPropValue(propMeta, bo, propValue);
					}

				}
			}
		}
	}

	@Override
	public Page<BoData> mapper(Page<?> pageData, BusinessObjectMetadata boMeta, Query query) throws BusinessException {
		List<BoData> dataList = new ArrayList<>();
		for (Object bo : pageData.getContent()) {
			dataList.add(this.mapper(bo, boMeta, query));
		}

		return new PageImpl<BoData>(dataList, query.getPageable(), pageData.getTotalElements());
	}

	private boolean isPropExposed(BusinessObjectPropertyMetadata propMeta, Query query) {

		if (propMeta.isExposed()) {
			if (query == null || !query.hasSelects()) {
				return true;
			}

			return query.isMatchSelect(propMeta.getName());
		}

		return false;
	}

	private boolean isPropCanMerge(BusinessObjectPropertyMetadata propMeta) {
		if (propMeta.isIdField()) {
			return false;
		}

		if (propMeta.isReadonly()) {
			return false;
		}

		return true;
	}

	private void setPropValue(BusinessObjectPropertyMetadata propMeta, Object bo, Object propValue)
			throws LocatableBusinessException {
		PropertyDescriptor prop = this.getPropertyDescriptor(propMeta);

		Method setter = prop.getWriteMethod();
		if (setter == null) {
			throw new LocatableBusinessException(ErrorCodes.Bo_Property_No_Set_Method, propMeta.getName());
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			setter.invoke(bo, mapper.convertValue(propValue, prop.getPropertyType()));
		} catch (Exception e) {
			throw new LocatableBusinessException(ErrorCodes.Bo_Property_Set_Value_Failed, propMeta.getName());
		}
	}

	private Object getPropValue(BusinessObjectPropertyMetadata propMeta, Object value)
			throws LocatableBusinessException {
		PropertyDescriptor prop = this.getPropertyDescriptor(propMeta);
		Method getter = prop.getReadMethod();
		if (getter == null) {
			throw new LocatableBusinessException(ErrorCodes.Bo_Property_No_Get_Method, propMeta.getName());
		}

		try {
			return getter.invoke(value);
		} catch (Exception e) {
			throw new LocatableBusinessException(ErrorCodes.Get_Bo_Property_Value_Failed, propMeta.getName());
		}
	}

	private PropertyDescriptor getPropertyDescriptor(BusinessObjectPropertyMetadata propMeta)
			throws LocatableBusinessException {
		PropertyDescriptor prop = BeanUtils.getPropertyDescriptor(propMeta.getBoMeta().getBoClass(),
				propMeta.getName());
		if (prop == null) {
			throw new LocatableBusinessException(ErrorCodes.Bo_Property_Not_Exist, propMeta.getName());
		}

		return prop;
	}

}
