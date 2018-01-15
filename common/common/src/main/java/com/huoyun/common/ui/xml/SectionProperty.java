package com.huoyun.common.ui.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "property")
public class SectionProperty implements XElement{

	private static final long serialVersionUID = -3861399930023413692L;
	
	@XmlAttribute(name = "ref", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	private String ref;

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
}
