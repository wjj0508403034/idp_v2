package com.huoyun.common.ui.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "header")
public class Header implements XElement{

	private static final long serialVersionUID = 8863720217935551811L;

	@XmlElement(name = "title")
	private Title title;

	@XmlElement(name = "subtitle")
	private SubTitle subTitle;

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public SubTitle getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(SubTitle subTitle) {
		this.subTitle = subTitle;
	}
}
