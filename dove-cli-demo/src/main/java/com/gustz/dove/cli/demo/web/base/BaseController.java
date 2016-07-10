package com.sinovatech.rd.wcsb.cli.demo.web.base;

import org.springframework.stereotype.Controller;

import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.sdk.web.WsCliCpOauthSlet;

/**
 * 
 * TODO: 基础类
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
@Controller
public abstract class BaseController {

    protected void setBaseReq(AbstBaseReq<?> req) throws Exception {
        req.setDevAcCode(WsCliCpOauthSlet.devAcCode);
        req.setCliAppPwd(WsCliCpOauthSlet.cliAppCode);
        req.setCliIpAddrs(WsCliCpOauthSlet.cliIpAddrs);
    }

}
