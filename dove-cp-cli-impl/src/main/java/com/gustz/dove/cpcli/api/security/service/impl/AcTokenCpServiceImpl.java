package com.gustz.dove.cpcli.api.security.service.impl;

import com.gustz.dove.cpcli.api.service.conf.SecurityWsUrl;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.security.rsp.AccessTokenRsp;
import com.sinovatech.rd.wcsb.cli.api.security.rsp.CallbackIpRsp;
import com.sinovatech.rd.wcsb.cli.api.security.service.impl.AcTokenServiceImpl;
import com.sinovatech.rd.wcsb.cli.api.service.cache.anno.AcTokenCacheP1p2;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.AcTokenCpService;

/**
 * TODO: 接口凭证服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 4, 2015 ]
 */
@Service
public class AcTokenCpServiceImpl extends AcTokenServiceImpl implements AcTokenCpService {

    /**
     * 获取接口凭证
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    @AcTokenCacheP1p2
    @Override
    public String getAccessTokenCp(long sn, String cliAppCode, String devAcCode) {
        //
        return this.getAccessToken(SecurityWsUrl.WSC07001CP, sn, cliAppCode, devAcCode);
    }

    /**
     * 获取接口凭证VO
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    @Override
    public AccessTokenRsp getAccessTokenVo(long sn, String cliAppCode, String devAcCode) {
        //
        return this.getAccessTokenVo(SecurityWsUrl.WSC07001CP, sn, cliAppCode, devAcCode);
    }

    /**
     * 获取微信服务器IP地址集
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    @Override
    public CallbackIpRsp getCallbackIp(long sn, String cliAppCode, String devAcCode) {
        //
        return this.getCallbackIp(SecurityWsUrl.WSC07002CP, sn, cliAppCode, devAcCode);
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(this.getAccessTokenCp(sn, cliAppCode, devAcCode));
    }

}
