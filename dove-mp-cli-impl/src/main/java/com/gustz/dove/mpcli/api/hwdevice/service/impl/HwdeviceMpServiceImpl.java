package com.gustz.dove.mpcli.api.hwdevice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.service.conf.WebsUrlParam;
import com.sinovatech.rd.wcsb.cli.api.service.impl.AbstBaseService;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.req.BindUserHwdReq;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.req.HwdBindOpenIdReq;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.req.HwdQrcodeReq;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.req.HwdeviceReq;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.BindHwdeviceRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.BindUserHwdRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.HwdBindOpenIdRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.HwdQrcodeRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.HwdStatusRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.rsp.HwdeviceRsp;
import com.sinovatech.rd.wcsb.mpcli.api.hwdevice.service.HwdeviceMpService;
import com.sinovatech.rd.wcsb.mpcli.api.security.service.AcTokenMpService;
import com.gustz.dove.mpcli.api.service.conf.HwdeviceWsUrl;

/**
 * 
 * TODO: 硬件设备服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class HwdeviceMpServiceImpl extends AbstBaseService<AbstBaseReq<?>> implements HwdeviceMpService {

    @Autowired
    private AcTokenMpService acTokenMpService;

    /**
     * 生成设备二维码
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public HwdQrcodeRsp createHwdQrcode(long sn, String cliAppCode, HwdQrcodeReq req) {
        HwdeviceWsUrl _wsUrl = HwdeviceWsUrl.WSC08001MP;
        //
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new HwdQrcodeRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 设备授权
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public HwdeviceRsp authHwDevice(long sn, String cliAppCode, HwdeviceReq req) {
        HwdeviceWsUrl _wsUrl = HwdeviceWsUrl.WSC08002MP;
        //
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new HwdeviceRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 获取设备ID和二维码（新接口）
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    @Override
    public CommRsp getHwdQrcode(long sn, String cliAppCode, String devAcCode) {
        HwdeviceWsUrl _wsUrl = HwdeviceWsUrl.WSC08003MP;
        //
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        //
        return this.httpGet(_wsUrl, sn, cliAppCode, new CommRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 查询设备状态
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param deviceId
     * @return
     */
    @Override
    public HwdStatusRsp getHwdStatus(long sn, String cliAppCode, String devAcCode, String deviceId) {
        HwdeviceWsUrl _wsUrl = HwdeviceWsUrl.WSC08008MP;
        //
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        Map<String, String> _map = new HashMap<String, String>();
        _map.put(WebsUrlParam.DEVICE_ID, deviceId);
        this.setWebsUrlParamMap(_map);
        //
        return this.httpGet(_wsUrl, sn, cliAppCode, new HwdStatusRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 获取设备绑定的OpenID
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public HwdBindOpenIdRsp getHwdBindOpenId(long sn, String cliAppCode, HwdBindOpenIdReq req) {
        HwdeviceWsUrl _wsUrl = HwdeviceWsUrl.WSC08010MP;
        //
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        Map<String, String> _map = new HashMap<String, String>();
        _map.put(WebsUrlParam.DEVICE_ID, req.getBody().getDeviceId());
        _map.put(WebsUrlParam.DEVICE_TYPE, req.getBody().getDeviceType());
        this.setWebsUrlParamMap(_map);
        // 
        return this.httpGet(_wsUrl, sn, cliAppCode, new HwdBindOpenIdRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 获取用户绑定的设备
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param openId
     * @return
     */
    @Override
    public BindHwdeviceRsp getBindHwdevice(long sn, String cliAppCode, String devAcCode, String openId) {
        HwdeviceWsUrl _wsUrl = HwdeviceWsUrl.WSC08011MP;
        //
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        Map<String, String> _map = new HashMap<String, String>();
        _map.put(WebsUrlParam.OPEN_ID, openId);
        this.setWebsUrlParamMap(_map);
        // 
        return this.httpGet(_wsUrl, sn, cliAppCode, new BindHwdeviceRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 绑定用户和设备
     * 
     * @param sn 
     * @param cliAppCode
     * @param devAcCode
     * @param req
     * @return
     */
    @Override
    public BindUserHwdRsp bindUHwd(long sn, String cliAppCode, BindUserHwdReq req) {
        //
        return this.doBindUserHwdReq(HwdeviceWsUrl.WSC08004MP, sn, cliAppCode, req);
    }

    /**
     * 解绑用户和设备
     * 
     * @param sn 
     * @param cliAppCode
     * @param devAcCode
     * @param req
     * @return
     */
    @Override
    public BindUserHwdRsp unbindUHwd(long sn, String cliAppCode, BindUserHwdReq req) {
        //
        return this.doBindUserHwdReq(HwdeviceWsUrl.WSC08005MP, sn, cliAppCode, req);
    }

    /**
     * 强制绑定用户和设备
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param req
     * @return
     */
    @Override
    public BindUserHwdRsp compelBindUHwd(long sn, String cliAppCode, BindUserHwdReq req) {
        //
        return this.doBindUserHwdReq(HwdeviceWsUrl.WSC08006MP, sn, cliAppCode, req);
    }

    /**
     * 强制绑定用户和设备
     * 
     * @param sn 
     * @param cliAppCode
     * @param devAcCode
     * @param req
     * @return
     */
    @Override
    public BindUserHwdRsp compelUnbindUHwd(long sn, String cliAppCode, BindUserHwdReq req) {
        //
        return this.doBindUserHwdReq(HwdeviceWsUrl.WSC08007MP, sn, cliAppCode, req);
    }

    private BindUserHwdRsp doBindUserHwdReq(HwdeviceWsUrl wsUrl, long sn, String cliAppCode, BindUserHwdReq req) {
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        // 
        return this.httpPost(wsUrl, sn, cliAppCode, req, new BindUserHwdRsp(sn, wsUrl.getWebsCode()));
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenMpService.getAccessTokenMp(sn, cliAppCode, devAcCode));
    }

}
