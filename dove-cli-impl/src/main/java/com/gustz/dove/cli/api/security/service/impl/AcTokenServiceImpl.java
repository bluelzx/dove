/*
 * @(#)AcTokenServiceImpl.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.gustz.dove.cli.api.security.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.gustz.dove.cli.api.account.service.impl.AccountService;
import com.gustz.dove.cli.api.app.service.impl.ClientAppService;
import com.gustz.dove.cli.api.service.conf.WebsUrlParam;
import com.gustz.dove.cli.api.service.impl.AbstBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gustz.dove.cli.api.account.vo.AccountVo;
import com.gustz.dove.cli.api.app.vo.ClientAppVo;
import com.sinovatech.rd.wcsb.cli.api.security.req.AccessTokenReq;
import com.sinovatech.rd.wcsb.cli.api.security.rsp.AccessTokenRsp;
import com.sinovatech.rd.wcsb.cli.api.security.rsp.CallbackIpRsp;
import com.sinovatech.rd.wcsb.cli.api.security.service.AcTokenService;
import com.sinovatech.rd.wcsb.cli.api.security.service.EncryptService;
import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;

/**
 * TODO: 接口凭证服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 4, 2015 ]
 */
public abstract class AcTokenServiceImpl extends AbstBaseService<AccessTokenReq> implements AcTokenService {

    @Autowired
    private ClientAppService clientAppService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EncryptService encryptService;

    /**
     * 获取接口凭证
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode
     * @param devAcCode 账号
     * @return
     */
    @Override
    public String getAccessToken(BaseWebsUrl websUrl, long sn, String cliAppCode, String devAcCode) {
        return this.getAccessTokenVo(websUrl, sn, cliAppCode, devAcCode).getBody().getAccessToken();
    }

    /**
     * 获取接口凭证VO
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode
     * @param devAcCode 账号
     * @return
     */
    @Override
    public AccessTokenRsp getAccessTokenVo(BaseWebsUrl websUrl, long sn, String cliAppCode, String devAcCode) {
        // 生成新的接口凭证
        AccountVo accountVo = accountService.getByCaCode(cliAppCode, devAcCode);
        // 请求参数
        Map<String, String> _paramMap = new HashMap<String, String>();
        _paramMap.put(WebsUrlParam.APP_ID, accountVo.getWecAppId()); // 凭证
        _paramMap.put(WebsUrlParam.APP_SECRET, accountVo.getWecAppSecret()); // 凭证密钥
        this.setWebsUrlParamMap(_paramMap);
        //
        return this.httpGet(websUrl, sn, cliAppCode, new AccessTokenRsp(sn, websUrl.getWebsCode()));
    }

    /**
     * 获取微信服务器IP地址集
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    @Override
    public CallbackIpRsp getCallbackIp(BaseWebsUrl websUrl, long sn, String cliAppCode, String devAcCode) {
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        // 
        return this.httpGet(websUrl, sn, cliAppCode, new CallbackIpRsp(sn, websUrl.getWebsCode()));
    }

    /**
     * 获取当前接入的开发者凭证
     * 
     * <pre>
     * 状态为S99的客户端APP（唯一）
     * </pre>
     * @return
     * @throws NoSuchAlgorithmException 
     */
    @Override
    public String getCurrDevToken() throws NoSuchAlgorithmException {
        ClientAppVo vo = clientAppService.getCurrCliApp();
        //
        return encryptService.getDevToken(vo.getAccountCode(), vo.getWecAppId());
    }

}
