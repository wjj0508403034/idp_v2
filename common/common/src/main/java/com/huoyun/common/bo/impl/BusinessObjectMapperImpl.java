package com.huoyun.common.bo.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.huoyun.common.bo.BoData;
import com.huoyun.common.bo.BusinessObjectMapper;
import com.huoyun.common.exceptions.BusinessException;
import com.huoyun.common.exceptions.ErrorCodes;
import com.huoyun.common.exceptions.LocatableBusinessException;
import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;

public class BusinessObjectMapperImpl implements BusinessObjectMapper {

	@Override
	public BoData mapper(Object value, BusinessObjectMetadata boMeta) throws BusinessException {
		if (value == null) {
			return null;
		}

		BoData boData = new BoData();

		for (BusinessObjectPropertyMetadata propMeta : boMeta.getPropertyMetadatas()) {
			if (this.isPropExposed(propMeta)) {
				boData.put(propMeta.getName(), this.getPropValue(propMeta, value));
			}
		}

		return boData;
	}

	@Override
	public Page<BoData> mapper(Page<?> pageData, BusinessObjectMetadata boMeta, Pageable pageable)
			throws BusinessException {
		List<BoData> dataList = new ArrayList<>();
		for (Object bo : pageData.getContent()) {
			dataList.add(this.mapper(bo, boMeta));
		}

		return new PageImpl<BoData>(dataList, pageable, pageData.getTotalElements());
	}

	private boolean isPropExposed(BusinessObjectPropertyMetadata propMeta) {
		return propMeta.isExposed();
	}

	private Object getPropValue(BusinessObjectPropertyMetadata propMeta, Object value)
			throws LocatableBusinessException {
		PropertyDescriptor prop = BeanUtils.getPropertyDescriptor(propMeta.getBoMeta().getBoClass(),
				propMeta.getName());
		Method getter = prop.getReadMethod();
		if (getter == null) {
			throw new LocatableBusinessException(ErrorCodes.Bo_Property_Not_Exist, propMeta.getName());
		}

		try {
			return getter.invoke(value);
		} catch (Exception e) {
			throw new LocatableBusinessException(ErrorCodes.Get_Bo_Property_Value_Failed, propMeta.getName());
		}
	}

}
