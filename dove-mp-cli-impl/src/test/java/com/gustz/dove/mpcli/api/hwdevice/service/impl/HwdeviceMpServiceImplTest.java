package com.gustz.dove.mpcli.api.hwdevice.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.req.BindUserHwdReq;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.req.BindUserHwdReq.BindUserHwdBodyReq;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.req.HwdBindOpenIdReq;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.req.HwdQrcodeReq;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.req.HwdQrcodeReq.HwdQrcodeBodyReq;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.req.HwdeviceReq;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.req.HwdeviceReq.HwdeviceBodyReq;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.BindHwdeviceRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.BindUserHwdRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.HwdBindOpenIdRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.HwdQrcodeRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.HwdStatusRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.HwdeviceRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.service.HwdeviceMpService;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.vo.HwdBaseInfo;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.vo.Hwdevice;
import com.gustz.dove.mpcli.api.service.base.MpCliTestBase;

/**
 * TODO:  硬件设备服务接口实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 16, 2015 ]
 */
public class HwdeviceMpServiceImplTest extends MpCliTestBase<String> {

    @Autowired
    private HwdeviceMpService service;

    private String openId = "o-bc2v98_6iKrDS2ELiD04yd8B5k";

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cli.api.hwdevice.service.impl.HwdeviceServiceImpl#createQrcode(long, java.lang.String, com.sinovatech.rd.wcsb.cli.api.hwdevice.req.HwdQrcodeReq)}.
     */
    @Test
    public void testCreateHwdQrcode() {
        int deviceNum = 1;
        String[] deviceIdList = new String[] {};
        HwdQrcodeReq req = new HwdQrcodeReq(devAcCode, new HwdQrcodeBodyReq(deviceNum, deviceIdList));
        //
        HwdQrcodeRsp rsp = service.createHwdQrcode(sn, cliAppCode, req);
        //
        System.out.println("createHwdQrcode-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

    @Test
    public void testAuthHwDevice() {
        int deviceNum = 1;
        HwdeviceBodyReq body = new HwdeviceBodyReq();
        body.setDeviceNum(deviceNum);
        body.setOpType("0");
        //
        Hwdevice[] hwdevice = new Hwdevice[] {};
        body.setDeviceList(hwdevice);
        //
        HwdeviceReq req = new HwdeviceReq(devAcCode, body);
        //
        HwdeviceRsp rsp = service.authHwDevice(sn, cliAppCode, req);
        //
        System.out.println("authHwDevice-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

    @Test
    public void testGetHwdQrcode() {
        CommRsp rsp = service.getHwdQrcode(sn, cliAppCode, devAcCode);
        //
        System.out.println("getHwdQrcode-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

    @Test
    public void testGetHwdStatus() {
        String deviceId = "";
        HwdStatusRsp rsp = service.getHwdStatus(sn, cliAppCode, devAcCode, deviceId);
        //
        System.out.println("getHwdStatus-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

    @Test
    public void testGetHwdBindOpenId() {
        String deviceId = "";
        String deviceType = "";
        HwdBaseInfo body = new HwdBaseInfo(deviceId, deviceType);
        HwdBindOpenIdReq req = new HwdBindOpenIdReq(devAcCode, body);
        //
        HwdBindOpenIdRsp rsp = service.getHwdBindOpenId(sn, cliAppCode, req);
        //
        System.out.println("getHwdBindOpenId-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

    @Test
    public void testGetBindHwdevice() {
        BindHwdeviceRsp rsp = service.getBindHwdevice(sn, cliAppCode, devAcCode, openId);
        //
        System.out.println("getBindHwdevice-rsp=:" + rsp.getBody().getRespMsg());
        System.out.println("getBindHwdevice-rsp-hwdlist=:" + rsp.getBody().getDeviceList());
        Assert.assertEquals(0, rsp.getBody().getRespMsg().getRetCode());
    }

    @Test
    public void testBindUHwd() {
        String deviceId = "";
        String ticket = "";
        BindUserHwdBodyReq body = new BindUserHwdBodyReq(openId, deviceId, ticket);
        BindUserHwdReq req = new BindUserHwdReq(devAcCode, body);
        //
        BindUserHwdRsp rsp = service.bindUHwd(sn, openId, req);
        //
        System.out.println("bindUHwd-rsp=:" + rsp.getBody().getBaseResp());
        Assert.assertEquals(0, rsp.getBody().getBaseResp().getErrCode());
    }

    @Test
    public void testGetUnbindHwdevice() {
        String deviceId = "";
        String ticket = "";
        BindUserHwdBodyReq body = new BindUserHwdBodyReq(openId, deviceId, ticket);
        BindUserHwdReq req = new BindUserHwdReq(devAcCode, body);
        //
        BindUserHwdRsp rsp = service.unbindUHwd(sn, openId, req);
        //
        System.out.println("unbindUHwd-rsp=:" + rsp.getBody().getBaseResp());
        Assert.assertEquals(0, rsp.getBody().getBaseResp().getErrCode());
    }

    @Test
    public void testCompelBindUHwd() {
        String deviceId = "123";
        BindUserHwdBodyReq body = new BindUserHwdBodyReq(openId, deviceId);
        BindUserHwdReq req = new BindUserHwdReq(devAcCode, body);
        //
        BindUserHwdRsp rsp = service.compelBindUHwd(sn, cliAppCode, req);
        //
        System.out.println("compelBindUHwd-rsp=:" + rsp.getBody().getBaseResp());
        Assert.assertEquals(0, rsp.getBody().getBaseResp().getErrCode());
    }

    @Test
    public void testCompelUnbindUHwd() {
        String deviceId = "";
        BindUserHwdBodyReq body = new BindUserHwdBodyReq(openId, deviceId);
        BindUserHwdReq req = new BindUserHwdReq(devAcCode, body);
        //
        BindUserHwdRsp rsp = service.compelUnbindUHwd(sn, cliAppCode, req);
        //
        System.out.println("compelUnbindUHwd-rsp=:" + rsp.getBody().getBaseResp());
        Assert.assertEquals(0, rsp.getBody().getBaseResp().getErrCode());
    }

}
