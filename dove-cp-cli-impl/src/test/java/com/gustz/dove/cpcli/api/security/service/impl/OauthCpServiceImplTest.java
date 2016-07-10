/*
 * @(#)OauthCpServiceImplTest.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.gustz.dove.cpcli.api.security.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cpcli.api.security.rsp.UserOAuthRsp;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.OauthCpService;
import com.gustz.dove.cpcli.api.service.base.CpCliTestBase;

/**
 * TODO: OAuth服务接口实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 14, 2015 ]
 */
public class OauthCpServiceImplTest extends CpCliTestBase<String> {

    @Autowired
    private OauthCpService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testGetSnsapiBaseUrl() {
        String state = "state123";
        String cbUrl = service.getSnsapiBaseUrl(cliAppCode, state);
        //
        System.out.println("getSnsapiBaseUrl-cbUrl=:" + cbUrl);
        Assert.assertNotNull(cbUrl);
    }

    @Test
    public void testGetSnsapiUserInfoUrl() {
        String state = "state123";
        String cbUrl = service.getSnsapiUserInfoUrl(cliAppCode, state);
        //
        System.out.println("getSnsapiUserInfoUrl-cbUrl=:" + cbUrl);
        Assert.assertNotNull(cbUrl);
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cpcli.api.security.service.impl.OauthServiceImpl#getUserByOAuth(long, java.lang.String, com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.UserBaseReq)}.
     */
    @Test
    public void testGetUserByOAuth() {
        String oauthCode = ""; // 页面跳转得到的OAuth授权code
        //
        UserOAuthRsp rsp = service.getUserByOAuth(sn, cliAppCode, devAcCode, oauthCode);
        //        
        System.out.println("getUserByOAuth-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

}
