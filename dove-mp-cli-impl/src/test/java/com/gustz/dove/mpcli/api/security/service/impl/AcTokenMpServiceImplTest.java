package com.gustz.dove.mpcli.api.security.service.impl;

import com.gustz.dove.mpcli.api.service.base.MpCliTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.security.rsp.AccessTokenRsp;
import com.sinovatech.rd.wcsb.cli.api.security.rsp.CallbackIpRsp;
import com.sinovatech.rd.wcsb.mpcli.api.security.service.AcTokenMpService;

/**
 * TODO: 接口凭证服务实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
public class AcTokenMpServiceImplTest extends MpCliTestBase<String> {

    @Autowired
    private AcTokenMpService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cli.api.security.service.impl.AcTokenServiceImpl#getAccessToken(long, java.lang.String, com.sinovatech.rd.wcsb.cli.api.security.req.AccessTokenReq)}.
     */
    @Test
    public void testGetAccessToken() {
        AccessTokenRsp _rsp = service.getAccessTokenVo(sn, cliAppCode, devAcCode);
        // 
        System.out.println("getAccessToken-rsp=:" + _rsp.getBody());
        Assert.assertNotNull(_rsp.getBody());
    }

    @Test
    public void testGetCallbackIp() {
        CallbackIpRsp _rsp = service.getCallbackIp(sn, cliAppCode, devAcCode);
        // 
        System.out.println("getCallbackIp-rsp=:" + _rsp.getBody().getIpList());
        Assert.assertNotNull(_rsp.getBody().getIpList());
    }

}
