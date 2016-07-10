package com.gustz.dove.cpcli.api.security.service;

import com.sinovatech.rd.wcsb.cli.api.security.rsp.AccessTokenRsp;
import com.sinovatech.rd.wcsb.cli.api.security.rsp.CallbackIpRsp;

/**
 * 
 * TODO: 接口凭证服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public interface AcTokenCpService {

    /**
     * 获取接口凭证
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    String getAccessTokenCp(long sn, String cliAppCode, String devAcCode);

    /**
     * 获取接口凭证VO
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    AccessTokenRsp getAccessTokenVo(long sn, String cliAppCode, String devAcCode);

    /**
     * 获取微信服务器IP地址集
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    CallbackIpRsp getCallbackIp(long sn, String cliAppCode, String devAcCode);

}