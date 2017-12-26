package com.huoyun.idp.admin.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huoyun.common.bo.BusinessObjectController;
import com.huoyun.idp.admin.config.AdminUrls;

@Controller
@RequestMapping(value = AdminUrls.REQUEST_URL_PREFIX)
public class AdminBoController extends BusinessObjectController {

}
