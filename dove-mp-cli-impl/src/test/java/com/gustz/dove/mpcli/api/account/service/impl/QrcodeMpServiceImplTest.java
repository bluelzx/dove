package com.gustz.dove.mpcli.api.account.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.mpcli.api.account.req.QrcodeImgReq;
import com.sinovatech.rd.wcsb.mpcli.api.account.req.QrcodeImgReq.QrcodeImgBodyReq;
import com.sinovatech.rd.wcsb.mpcli.api.account.req.QrcodeReq;
import com.sinovatech.rd.wcsb.mpcli.api.account.req.QrcodeReq.QrcodeBodyReq;
import com.sinovatech.rd.wcsb.mpcli.api.account.rsp.GetQrcodeImgRsp;
import com.sinovatech.rd.wcsb.mpcli.api.account.rsp.QrcodeRsp;
import com.sinovatech.rd.wcsb.mpcli.api.account.service.QrcodeMpService;
import com.sinovatech.rd.wcsb.mpcli.api.account.vo.QrcodeInfo;
import com.sinovatech.rd.wcsb.mpcli.api.account.vo.QrcodeInfo.Scene;
import com.sinovatech.rd.wcsb.mpcli.api.account.vo.QrcodeType;
import com.gustz.dove.mpcli.api.service.base.MpCliTestBase;

/**
 * TODO: 二维码服务的接口实现测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 13, 2015 ]
 */
public class QrcodeMpServiceImplTest extends MpCliTestBase<String> {

    @Autowired
    private QrcodeMpService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link QrcodeMpServiceImpl#createQrcode(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.account.req.QrcodeReq)}.
     */
    @Test
    public void testCreateQrcode() {
        QrcodeBodyReq body = new QrcodeBodyReq(QrcodeType.QR_SCENE, new QrcodeInfo(new Scene(123456)));
        QrcodeReq req = new QrcodeReq(devAcCode, body);
        //
        QrcodeRsp _rsp = service.createQrcode(sn, cliAppCode, req);
        System.out.println("createQrcode-rsp=:" + _rsp.getBody().getTicket());
        Assert.assertNotNull(_rsp.getBody().getTicket());
    }

    /**
     * Test method for {@link QrcodeMpServiceImpl#getQrcodeImg(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.account.req.QrcodeImgReq)}.
     */
    @Test
    public void testGetQrcodeImg() {
        String ticket = "gQFd8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL1BrU0p2cmZsMHJHclY4UTVGV285AAIEwErPVQMEPAAAAA==";
        QrcodeImgReq req = new QrcodeImgReq(devAcCode, new QrcodeImgBodyReq(ticket));
        //
        GetQrcodeImgRsp _rsp = service.getQrcodeImg(sn, cliAppCode, req);
        System.out.println("getQrcodeImg-rsp=:" + _rsp.getBody().getUri());
        Assert.assertNotNull(_rsp.getBody().getUri());
    }

}
