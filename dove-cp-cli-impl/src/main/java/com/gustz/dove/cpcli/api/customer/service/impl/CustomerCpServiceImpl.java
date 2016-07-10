package com.gustz.dove.cpcli.api.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.impl.AbstBaseService;
import com.sinovatech.rd.wcsb.cpcli.api.customer.rsp.CommCustRsp;
import com.sinovatech.rd.wcsb.cpcli.api.customer.service.CustomerCpService;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.AcTokenCpService;
import com.gustz.dove.cpcli.api.service.conf.CustomerWsUrl;

/**
 * 
 * TODO: 客服的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class CustomerCpServiceImpl extends AbstBaseService<CustBaseReq<?>> implements CustomerCpService {

    @Autowired
    private AcTokenCpService acTokenCpService;

    /**
     * 发送客服消息
     * 
     * @param sn
     * @param cliAppCode
     * @param req 
     * @return
     */
    @Override
    public CommCustRsp sendCustomerMsg(long sn, String cliAppCode, CustBaseReq<?> req) {
        CustomerWsUrl _wsUrl = CustomerWsUrl.WSC03001CP;
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new CommCustRsp(sn, _wsUrl.getWebsCode()));
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenCpService.getAccessTokenCp(sn, cliAppCode, devAcCode));
    }

}
