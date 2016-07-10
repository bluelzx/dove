package com.gustz.dove.mpcli.api.account.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.gustz.dove.mpcli.api.service.conf.AccountWsUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.service.conf.WebsUrlParam;
import com.sinovatech.rd.wcsb.cli.api.service.impl.AbstBaseService;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.mpcli.api.account.req.QrcodeImgReq;
import com.sinovatech.rd.wcsb.mpcli.api.account.req.QrcodeReq;
import com.sinovatech.rd.wcsb.mpcli.api.account.rsp.GetQrcodeImgRsp;
import com.sinovatech.rd.wcsb.mpcli.api.account.rsp.QrcodeRsp;
import com.sinovatech.rd.wcsb.mpcli.api.account.service.QrcodeMpService;
import com.sinovatech.rd.wcsb.mpcli.api.security.service.AcTokenMpService;

/**
 * 
 * TODO: 二维码服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class QrcodeMpServiceImpl extends AbstBaseService<AbstBaseReq<?>> implements QrcodeMpService {

    @Autowired
    private AcTokenMpService acTokenMpService;

    /**
     * 生成二维码
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public QrcodeRsp createQrcode(long sn, String cliAppCode, QrcodeReq req) {
        AccountWsUrl _wsUrl = AccountWsUrl.WSC07001MP;
        //
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new QrcodeRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 获取二维码图片地址
     * 
     * @param sn
     * @param cliAppCode 
     * @param req
     * @return
     */
    @Override
    public GetQrcodeImgRsp getQrcodeImg(long sn, String cliAppCode, QrcodeImgReq req) {
        AccountWsUrl _wsUrl = AccountWsUrl.WSC07002MP;
        //
        Map<String, String> _map = new HashMap<String, String>();
        _map.put(WebsUrlParam.TICKET, req.getBody().getTicket());
        this.setWebsUrlParamMap(_map);
        //
        return this.downloadFile(_wsUrl, sn, cliAppCode, req, new GetQrcodeImgRsp(sn, _wsUrl.getWebsCode()));
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenMpService.getAccessTokenMp(sn, cliAppCode, devAcCode));
    }

}
