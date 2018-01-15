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
@XmlRootElement(name = "root")
public class Root implements XElement {

	private static final long serialVersionUID = 6918662047422397803L;

	@XmlAttribute(name = "name", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	private String name;

	@XmlAttribute(name = "namespace", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	private String namespace;

	@XmlElement(name = "header")
	private Header header;

	@XmlElementWrapper(name = "sections", required = true)
	@XmlElement(name = "section")
	private List<Section> sections;

	@XmlElement(name = "listview")
	private ListView listview;

	@XmlElementWrapper(name = "filters", required = true)
	@XmlElement(name = "filter")
	private List<Filter> filters;

	@XmlElementWrapper(name = "properties", required = true)
	@XmlElement(name = "property")
	private List<Property> properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public ListView getListview() {
		return listview;
	}

	public void setListview(ListView listview) {
		this.listview = listview;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
}
