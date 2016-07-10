package com.gustz.dove.mpcli.api.security.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.mpcli.api.security.req.AcTokenOAuthReq;
import com.sinovatech.rd.wcsb.mpcli.api.security.req.AcTokenOAuthReq.AcTokenOAuthBodyReq;
import com.sinovatech.rd.wcsb.mpcli.api.security.rsp.AcTokenOAuthRsp;
import com.sinovatech.rd.wcsb.mpcli.api.security.service.OauthMpService;
import com.gustz.dove.mpcli.api.service.base.MpCliTestBase;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserReq.UserBodyReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.rsp.UserRsp;

/**
 * TODO: OAuth服务接口实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 14, 2015 ]
 */
public class OauthMpServiceImplTest extends MpCliTestBase<String> {

    @Autowired
    private OauthMpService service;

    private String openId = "o-bc2v98_6iKrDS2ELiD04yd8B5k";

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
     * Test method for {@link OauthMpServiceImpl#getOAuthAcToken(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.security.req.AcTokenOAuthReq)}.
     */
    @Test
    public void testGetOAuthAcToken() {
        String code = ""; // getOAuthUrl 授权后得到的CODE
        AcTokenOAuthBodyReq body = new AcTokenOAuthBodyReq();
        body.setAppId(appId);
        body.setAppSecret(appSecret);
        body.setCode(code);
        //
        AcTokenOAuthReq req = new AcTokenOAuthReq(devAcCode, body);
        AcTokenOAuthRsp rsp = service.getOAuthAcToken(sn, cliAppCode, req);
        //
        System.out.println("getOAuthAcToken-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    /**
     * Test method for {@link OauthMpServiceImpl#getUserByOAuth(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.user.req.UserReq)}.
     */
    @Test
    public void testGetUserByOAuth() {
        String oauthAcToken = ""; // getOAuthAcToken 得到的OAuth凭证
        UserReq req = new UserReq(devAcCode, new UserBodyReq(openId));
        //
        UserRsp rsp = service.getUserByOAuth(sn, openId, oauthAcToken, req);
        //        
        System.out.println("getUserByOAuth-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

}
