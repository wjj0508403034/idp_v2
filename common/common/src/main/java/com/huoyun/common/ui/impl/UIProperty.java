package com.huoyun.common.ui.impl;

import org.apache.commons.lang3.StringUtils;

import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;

public class UIProperty {

	private String name;
	private String namespace;
	private String label;
	private boolean mandatory;
	private boolean readonly;
	private boolean searchable;

	public UIProperty(BusinessObjectPropertyMetadata propMeta) {
		this.name = propMeta.getName();
		this.namespace = propMeta.getNamespace();
		this.label = propMeta.getLabel();
		this.mandatory = propMeta.isMandatory();
		this.readonly = propMeta.isReadonly();
		this.searchable = propMeta.isSearchable();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		if (StringUtils.isEmpty(label)) {
			return name;
		}
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

}