package com.gustz.dove.mpcli.api.hwdevice.service;

import javax.jws.WebService;

import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.gustz.dove.mpcli.api.hwdevice.req.BindUserHwdReq;
import com.gustz.dove.mpcli.api.hwdevice.req.HwdBindOpenIdReq;
import com.gustz.dove.mpcli.api.hwdevice.req.HwdQrcodeReq;
import com.gustz.dove.mpcli.api.hwdevice.req.HwdeviceReq;
import com.gustz.dove.mpcli.api.hwdevice.rsp.BindHwdeviceRsp;
import com.gustz.dove.mpcli.api.hwdevice.rsp.BindUserHwdRsp;
import com.gustz.dove.mpcli.api.hwdevice.rsp.HwdBindOpenIdRsp;
import com.gustz.dove.mpcli.api.hwdevice.rsp.HwdQrcodeRsp;
import com.gustz.dove.mpcli.api.hwdevice.rsp.HwdStatusRsp;
import com.gustz.dove.mpcli.api.hwdevice.rsp.HwdeviceRsp;

/**
 * 
 * TODO: 硬件设备服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 16, 2015 ]
 */
@WebService
public interface HwdeviceMpService {

    /**
     * 生成设备二维码
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    HwdQrcodeRsp createHwdQrcode(long sn, String cliAppCode, HwdQrcodeReq req);

    /**
     * 设备授权
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    HwdeviceRsp authHwDevice(long sn, String cliAppCode, HwdeviceReq req);

    /**
     * 获取设备ID和二维码（新接口）
     * 
     * @param sn
     * @param cliAppCode
     * @param acCode
     * @return
     */
    CommRsp getHwdQrcode(long sn, String cliAppCode, String acCode);

    /**
     * 查询设备状态
     * 
     * @param sn
     * @param cliAppCode
     * @param acCode
     * @param deviceId
     * @return
     */
    HwdStatusRsp getHwdStatus(long sn, String cliAppCode, String acCode, String deviceId);

    /**
     * 获取设备绑定的OpenID
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    HwdBindOpenIdRsp getHwdBindOpenId(long sn, String cliAppCode, HwdBindOpenIdReq req);

    /**
     * 获取用户绑定的设备
     * 
     * @param sn
     * @param cliAppCode
     * @param acCode
     * @param openId
     * @return
     */
    BindHwdeviceRsp getBindHwdevice(long sn, String cliAppCode, String acCode, String openId);

    /**
     * 绑定用户和设备
     * 
     * @param sn 
     * @param cliAppCode
     * @param acCode
     * @param req
     * @return
     */
    BindUserHwdRsp bindUHwd(long sn, String cliAppCode, BindUserHwdReq req);

    /**
     * 解绑用户和设备
     * 
     * @param sn 
     * @param cliAppCode
     * @param acCode
     * @param req
     * @return
     */
    BindUserHwdRsp unbindUHwd(long sn, String cliAppCode, BindUserHwdReq req);

    /**
     * 强制绑定用户和设备
     * 
     * @param sn 
     * @param cliAppCode
     * @param acCode
     * @param req
     * @return
     */
    BindUserHwdRsp compelBindUHwd(long sn, String cliAppCode, BindUserHwdReq req);

    /**
     * 强制解绑用户和设备
     * 
     * @param sn 
     * @param cliAppCode
     * @param acCode
     * @param req
     * @return
     */
    BindUserHwdRsp compelUnbindUHwd(long sn, String cliAppCode, BindUserHwdReq req);

}