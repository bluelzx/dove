package com.gustz.dove.mpcli.api.customer.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.customer.vo.MediaCust;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.mpcli.api.customer.req.ImageCustReq;
import com.sinovatech.rd.wcsb.mpcli.api.customer.req.ImageCustReq.ImageBodyCustReq;
import com.sinovatech.rd.wcsb.mpcli.api.customer.service.CustomerMpService;
import com.gustz.dove.mpcli.api.service.base.MpCliTestBase;

/**
 * TODO: 客服接口实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 7, 2015 ]
 */
public class CustomerMpServiceImplTest extends MpCliTestBase<String> {

    @Autowired
    private CustomerMpService service;

    @SuppressWarnings("unused")
    private String openId = "o-bc2v2Db7zDsSjC4sZ7DbDYli0A";

    private String openId2 = "o-bc2v98_6iKrDS2ELiD04yd8B5k";

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link CustomerMpServiceImpl#sendCustomerMsg(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.customer.req.CustBaseReq)}.
     */
    @Test
    public void testSendCustomerMsg() {
        String mediaId = "4-seKNC5lH-kLUOK26_28Y-Ce3GQK69QMW5Zb0IgjbgsoL-SctlyILb2kfk929QV";
        ImageBodyCustReq body = new ImageBodyCustReq(openId2, new MediaCust(mediaId));
        ImageCustReq req = new ImageCustReq(devAcCode, body);
        // do
        CommRsp rsp = service.sendCustomerMsg(sn, cliAppCode, req);
        System.out.println("customerService.sendCustomerMsg-rsp=:" + rsp.getBody());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }
}
