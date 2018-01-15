package com.huoyun.common.ui.metadata.impl;

import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectMetadataRepository;
import com.huoyun.common.service.AbstractBusinessService;
import com.huoyun.common.ui.UIMeta;
import com.huoyun.common.ui.UIMetaLoader;
import com.huoyun.common.ui.impl.UIMetaImpl;
import com.huoyun.common.ui.metadata.UIMetadataRepository;
import com.huoyun.common.ui.xml.Root;

public class UIMetadataRepositoryImpl extends AbstractBusinessService implements
		UIMetadataRepository {

	@Override
	public UIMeta getUiMeta(String boNamespace, String boName) {
		BusinessObjectMetadata boMeta = this.boMetaRepo().getBoMeta(
				boNamespace, boName);
		if (boMeta == null) {
			throw new RuntimeException(String.format(
					"Bo (%s.%s) entity not defined", boNamespace, boName));
		}

		UIMetaImpl uiMeta = new UIMetaImpl(boMeta);
		Root uiElement = this.uiMetaLoader().getUiElement(boNamespace, boName);
		if (uiElement != null) {
			uiMeta.mergeXml(uiElement);
		}
		return uiMeta;
	}

	private UIMetaLoader uiMetaLoader() {
		return this.getBean(UIMetaLoader.class);
	}

	private BusinessObjectMetadataRepository boMetaRepo() {
		return this.getBean(BusinessObjectMetadataRepository.class);
	}

}
