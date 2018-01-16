package com.huoyun.common.ui.impl;

import java.util.ArrayList;
import java.util.List;

import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectPropertyMetadata;
import com.huoyun.common.ui.UIMeta;
import com.huoyun.common.ui.xml.ListView;
import com.huoyun.common.ui.xml.Root;
import com.huoyun.common.ui.xml.Section;

public class UIMetaImpl implements UIMeta {

	private String boName;
	private String boNamespace;
	private String label;

	private List<UISection> sections = new ArrayList<>();
	private UIListView listview;
	private List<UIProperty> properties = new ArrayList<>();

	public UIMetaImpl(BusinessObjectMetadata boMeta) {
		this.boName = boMeta.getName();
		this.boNamespace = boMeta.getNamespace();
		this.label = boMeta.getLabel();

		for (BusinessObjectPropertyMetadata propMeta : boMeta
				.getPropertyMetadatas()) {
			if (propMeta.isExposed()) {
				this.properties.add(new UIProperty(propMeta));
			}
		}
	}

	public UIMeta mergeXml(Root root) {
		ListView listView = root.getListview();
		if (listView != null) {
			this.listview = new UIListView(listView);
		}

		for (Section section : root.getSections()) {
			this.sections.add(new UISection(section));
		}

		// for(Property prop: root.getProperties()){
		//
		// }

		return this;
	}

	@Override
	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	@Override
	public String getBoNamespace() {
		return boNamespace;
	}

	public void setBoNamespace(String boNamespace) {
		this.boNamespace = boNamespace;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public List<UISection> getSections() {
		return sections;
	}

	public void setSections(List<UISection> sections) {
		this.sections = sections;
	}

	@Override
	public UIListView getListview() {
		return listview;
	}

	public void setListview(UIListView listview) {
		this.listview = listview;
	}

	@Override
	public List<UIProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<UIProperty> properties) {
		this.properties = properties;
	}

}
