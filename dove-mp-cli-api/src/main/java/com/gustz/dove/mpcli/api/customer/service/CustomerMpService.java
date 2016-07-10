package com.gustz.dove.mpcli.api.customer.service;

import javax.jws.WebService;

import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;

/**
 * 
 * TODO: 客服的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
@WebService
public interface CustomerMpService {

    /**
     * 发送客服消息
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    CommRsp sendCustomerMsg(long sn, String cliAppCode, CustBaseReq<?> req);

}