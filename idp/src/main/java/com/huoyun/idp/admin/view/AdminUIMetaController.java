package com.huoyun.idp.admin.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.huoyun.idp.admin.config.AdminUrls;
import com.huoyun.common.ui.UIMetaController;

@Controller
@RequestMapping(value = AdminUrls.REQUEST_URL_PREFIX)
public class AdminUIMetaController extends UIMetaController {

}
