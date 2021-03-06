package com.huoyun.common.metadata.impl;

import java.lang.reflect.Field;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huoyun.common.localization.LocalizationService;
import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectMetadataConfigurer;
import com.huoyun.common.metadata.BusinessObjectMetadataConfigurerAdapter;
import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;
import com.huoyun.common.metadata.NamespaceConstants;
import com.huoyun.common.metadata.annotation.BusinessObjectProperty;

public class BusinessObjectPropertyMetadataImpl implements BusinessObjectPropertyMetadata {

	private String namespace = NamespaceConstants.SYSTEM_BUSINESS_OBJECT_PROPERTY_NAMESPACE;
	private String name;
	private boolean mandatory;
	private boolean readonly;
	private boolean searchable;
	private String label;
	private final BusinessObjectMetadataImpl boMeta;
	private Field field;
	private boolean exposed;
	private boolean idField;

	public BusinessObjectPropertyMetadataImpl(BusinessObjectMetadataImpl businessObjectMetadataImpl, Field field,
			BusinessObjectProperty boPropAnnotation) {
		this.field = field;
		this.boMeta = businessObjectMetadataImpl;
		this.name = field.getName();
		this.readonly = boPropAnnotation.readonly();
		this.searchable = boPropAnnotation.searchable();
		this.mandatory = boPropAnnotation.mandatory();
		this.exposed = boPropAnnotation.exposed();
		this.setLabel(this.getLocalizedLabel());

		Id idAnnotation = field.getAnnotation(Id.class);
		this.idField = idAnnotation != null;
	}

	@Override
	public String getIdentityName() {
		return String.format("%s.%s", this.getNamespace(), this.getName());
	}

	@Override
	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	@Override
	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	@Override
	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	@JsonIgnore
	@Override
	public BusinessObjectMetadata getBoMeta() {
		return boMeta;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@JsonIgnore
	@Override
	public Class<?> getRuntimeType() {
		return this.field.getType();
	}

	@JsonIgnore
	@Override
	public boolean isExposed() {
		return exposed;
	}

	@JsonIgnore
	@Override
	public boolean isIdField() {
		return idField;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	private String getLocalizedLabel() {
		String messageKey = this.boMetaConfigurer().getBoPropertyLabelLocalizationPattern();
		return this.localizationService()
				.getMessage(String.format(messageKey, this.getBoMeta().getName(), this.getName()));
	}

	private LocalizationService localizationService() {
		return ((BusinessObjectMetadataImpl) this.getBoMeta()).getBean(LocalizationService.class);
	}

	private BusinessObjectMetadataConfigurer boMetaConfigurer() {
		return ((BusinessObjectMetadataImpl) this.getBoMeta()).getBean(BusinessObjectMetadataConfigurerAdapter.class)
				.getBoMetaConfigurer();
	}

}
