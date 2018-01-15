package com.huoyun.common.ui;

import com.huoyun.common.ui.xml.Root;

public interface UIMetaLoader {

	Root getUiElement(String boNamespace, String boName);

}
