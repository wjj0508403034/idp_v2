package com.huoyun.common.ui.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "section")
public class Section implements XElement{

	private static final long serialVersionUID = 6687784241125698605L;

	@XmlElementWrapper(name = "properties", required = true)
	@XmlElement(name = "property")
	private List<SectionProperty> properties;

	@XmlAttribute(name = "label")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	private String labelKey;

	public List<SectionProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<SectionProperty> properties) {
		this.properties = properties;
	}

	public String getLabelKey() {
		return labelKey;
	}

	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
}
