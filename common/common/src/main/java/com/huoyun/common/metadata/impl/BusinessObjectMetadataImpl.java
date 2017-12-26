package com.huoyun.common.metadata.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huoyun.common.localization.LocalizationService;
import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectMetadataConfigurer;
import com.huoyun.common.metadata.BusinessObjectMetadataConfigurerAdapter;
import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;
import com.huoyun.common.metadata.NamespaceConstants;
import com.huoyun.common.metadata.annotation.BusinessObject;
import com.huoyun.common.metadata.annotation.BusinessObjectProperty;
import com.huoyun.common.service.AbstractBusinessService;

public class BusinessObjectMetadataImpl extends AbstractBusinessService implements BusinessObjectMetadata {

	private String name;
	private String namespace = NamespaceConstants.SYSTEM_BUSINESS_OBJECT_NAMESPACE;
	private boolean exposed;
	private String label;
	private final Map<String, BusinessObjectPropertyMetadata> propertyMetadatasMap = new HashMap<>();
	private final Class<? extends BusinessObject> boClass;

	public BusinessObjectMetadataImpl(Class<? extends BusinessObject> boClass, ApplicationContext applicationContext) {
		super.setApplicationContext(applicationContext);
		this.boClass = boClass;
		BusinessObject boAnnotation = boClass.getAnnotation(BusinessObject.class);

		this.name = boAnnotation.name();
		if (StringUtils.isEmpty(this.name)) {
			this.name = boClass.getSimpleName();
		}

		this.exposed = boAnnotation.exposed();
		this.label = this.getLocalizedLabel();
		this.initProps(boClass);
	}

	@Override
	public String getIdentityName() {
		return String.format("%s.%s", this.getNamespace(), this.getName());
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@JsonGetter("properties")
	@Override
	public List<BusinessObjectPropertyMetadata> getPropertyMetadatas() {
		return new ArrayList<BusinessObjectPropertyMetadata>(this.propertyMetadatasMap.values());
	}

	@Override
	public BusinessObjectPropertyMetadata getPropertyMetadata(String propertyNamespace, String propertyName) {
		return this.propertyMetadatasMap.get(this.getPropertyIdentityName(propertyNamespace, propertyName));
	}

	@Override
	public BusinessObjectPropertyMetadata getPropertyMetadata(String propertyName) {
		return this.getPropertyMetadata(NamespaceConstants.SYSTEM_BUSINESS_OBJECT_PROPERTY_NAMESPACE, propertyName);
	}

	@JsonIgnore
	@Override
	public Class<? extends BusinessObject> getBoClass() {
		return boClass;
	}

	private String getPropertyIdentityName(String propertyNamespace, String propertyName) {
		return String.format("%s.%s", propertyNamespace, propertyName);
	}

	private LocalizationService localizationService() {
		return this.getBean(LocalizationService.class);
	}

	private BusinessObjectMetadataConfigurer boMetaConfigurer() {
		return this.getBean(BusinessObjectMetadataConfigurerAdapter.class).getBoMetaConfigurer();
	}

	private String getLocalizedLabel() {
		String messageKey = this.boMetaConfigurer().getBoLabelLocalizationPattern();
		return this.localizationService().getMessage(String.format(messageKey, this.getName()));
	}

	private void initProps(Class<?> klass) {
		for (Field field : klass.getDeclaredFields()) {
			BusinessObjectProperty boPropAnnotation = field.getAnnotation(BusinessObjectProperty.class);
			if (boPropAnnotation != null) {
				BusinessObjectPropertyMetadata boPropMeta = new BusinessObjectPropertyMetadataImpl(this, field,
						boPropAnnotation);
				this.propertyMetadatasMap.put(boPropMeta.getIdentityName(), boPropMeta);
			}
		}

		if (klass.getSuperclass() != null) {
			this.initProps(klass.getSuperclass());
		}
	}

}
