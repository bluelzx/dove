package com.gustz.dove.mpcli.api.hwdevice.req;

import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.gustz.dove.mpcli.api.hwdevice.vo.HwdBaseInfo;

/**
 * 
 * TODO:  设备绑定OpenID请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class HwdBindOpenIdReq extends AbstBaseReq<HwdBaseInfo> {

    private static final long serialVersionUID = 1L;

    public HwdBindOpenIdReq(String devAcCode, HwdBaseInfo body) {
        super(devAcCode, body);
    }

}
