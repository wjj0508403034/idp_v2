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
@XmlRootElement(name = "listview")
public class ListView implements XElement{

	private static final long serialVersionUID = -277779374034041030L;

	@XmlAttribute(name = "select", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	private String select;

	@XmlAttribute(name = "orderby", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	private String orderby;

	@XmlAttribute(name = "sortProperty", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	private String sortProperty;

	@XmlElementWrapper(name = "columns", required = true)
	@XmlElement(name = "column")
	private List<Column> columns;

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}
}
