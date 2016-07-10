package com.sinovatech.rd.wcsb.cli.demo.web.wcsb;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinovatech.rd.wcsb.cli.demo.user.service.impl.UserOauthServiceImpl;
import com.sinovatech.rd.wcsb.cli.sdk.base.service.UserOauthService;
import com.sinovatech.rd.wcsb.cli.sdk.web.WsCliCpOauthSlet;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.OauthCpService;

/**
 * TODO: 项目管理平台的微信授权C
 *
 * @author ZHENFENG ZHANG
 * @since [ Nov 8, 2015 ]
 */
@Controller
@RequestMapping("")
public class WscOauthController extends WsCliCpOauthSlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserOauthServiceImpl userOauthService;

    @Autowired
    private OauthCpService oauthCpService;

    @PostConstruct
    private void load() throws ServletException {
        super.init();
    }

    @RequestMapping("/wcsbus/cp/doCpOauth")
    public void doCpOauth(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    /**
     * 获取微信用户OAUTH授权服务
     * 
     * @return
     */
    @Override
    protected UserOauthService getUserOauthService() {
        return this.userOauthService;
    }

    /**
     * 获取微信OAUTH授权CP服务
     * 
     * @return
     */
    @Override
    protected OauthCpService getOauthCpService() {
        return this.oauthCpService;
    }

}
