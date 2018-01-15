package com.huoyun.common.ui.impl;

import java.util.ArrayList;
import java.util.List;

import com.huoyun.common.ui.xml.Section;
import com.huoyun.common.ui.xml.SectionProperty;

public class UISection {

	private String label;
	private List<String> properties = new ArrayList<>();

	public UISection() {

	}

	public UISection(Section section) {
		this.label = section.getLabelKey();

		for (SectionProperty prop : section.getProperties()) {
			this.properties.add(prop.getRef());
		}
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<String> getProperties() {
		return properties;
	}

	public void setProperties(List<String> properties) {
		this.properties = properties;
	}

}
