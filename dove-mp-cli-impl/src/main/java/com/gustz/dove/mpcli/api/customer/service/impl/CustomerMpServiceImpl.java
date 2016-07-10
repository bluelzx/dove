package com.gustz.dove.mpcli.api.customer.service.impl;

import com.gustz.dove.mpcli.api.service.conf.CustomerWsUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.impl.AbstBaseService;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.mpcli.api.customer.service.CustomerMpService;
import com.sinovatech.rd.wcsb.mpcli.api.security.service.AcTokenMpService;

/**
 * 
 * TODO: 客服的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class CustomerMpServiceImpl extends AbstBaseService<CustBaseReq<?>> implements CustomerMpService {

    @Autowired
    private AcTokenMpService acTokenMpService;

    /**
     * 发送客服消息
     * 
     * @param sn
     * @param cliAppCode
     * @param req 
     * @return
     */
    @Override
    public CommRsp sendCustomerMsg(long sn, String cliAppCode, CustBaseReq<?> req) {
        CustomerWsUrl _wsUrl = CustomerWsUrl.WSC06001MP;
        //
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new CommRsp(sn, _wsUrl.getWebsCode()));
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenMpService.getAccessTokenMp(sn, cliAppCode, devAcCode));
    }

}
