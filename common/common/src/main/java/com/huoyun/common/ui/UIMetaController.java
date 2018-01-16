package com.huoyun.common.ui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huoyun.common.service.AbstractBusinessService;
import com.huoyun.common.ui.metadata.UIMetadataRepository;

public abstract class UIMetaController extends AbstractBusinessService{

	@RequestMapping(value = "/ui-meta", method = RequestMethod.GET)
	@ResponseBody
	public UIMeta getBoMetadata(@RequestParam("boNamespace") String boNamespace,
			@RequestParam("boName") String boName) {
		return this.uiMetaRepository().getUiMeta(boNamespace, boName);
	}

	private UIMetadataRepository uiMetaRepository() {
		return this.getBean(UIMetadataRepository.class);
	}
}
