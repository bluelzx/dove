package com.sinovatech.rd.wcsb.cli.demo.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.sdk.base.service.UserOauthService;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.vo.CpUser;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.OauthCpService;

/**
 * TODO: 微信用户OAUTH授权的实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Nov 7, 2015 ]
 */
@Service
public class UserOauthServiceImpl implements UserOauthService {

    @Autowired
    private OauthCpService oauthCpService;

    /**
     * 获取微信登录的CP用户
     * 
     * @param userId
     * @param deviceId
     * @return
     */
    @Override
    public CpUser getLoginCpUser(String userId, String deviceId) {
        return null;
    }

    /**
     * 获取授权失败后跳转URI
     * 
     * @return
     */
    @Override
    public String getOauthFailUri() {
        return "/views/error.html";
    }

}
