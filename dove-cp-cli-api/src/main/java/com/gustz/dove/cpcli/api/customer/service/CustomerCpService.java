package com.gustz.dove.cpcli.api.customer.service;

import javax.jws.WebService;

import com.gustz.dove.cpcli.api.customer.rsp.CommCustRsp;
import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;

/**
 * 
 * TODO: 客服的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
@WebService
public interface CustomerCpService {

    /**
     * 发送客服消息
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    CommCustRsp sendCustomerMsg(long sn, String cliAppCode, CustBaseReq<?> req);

}