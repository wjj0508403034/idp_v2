package com.huoyun.common.ui.ext.impl;

import com.huoyun.common.service.AbstractBusinessService;
import com.huoyun.common.ui.ext.ListViewExt;
import com.huoyun.common.ui.ext.ListViewExtRepository;
import com.huoyun.common.ui.ext.UIMetaExtService;

public class UIMetaExtServiceImpl extends AbstractBusinessService implements UIMetaExtService{

	@Override
	public ListViewExt findListViewExt(Long ownerId){
		return this.listViewExtRepository().findByOwnerId(ownerId);
	}
	
	private ListViewExtRepository listViewExtRepository(){
		return this.getBean(ListViewExtRepository.class);
	}
}
