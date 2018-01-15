package com.huoyun.common.ui;

import java.util.List;

import com.huoyun.common.ui.impl.UIListView;
import com.huoyun.common.ui.impl.UIProperty;
import com.huoyun.common.ui.impl.UISection;

public interface UIMeta {

	String getBoName();

	String getBoNamespace();

	String getLabel();

	List<UISection> getSections();

	UIListView getListview();

	List<UIProperty> getProperties();
}
