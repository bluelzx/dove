package com.gustz.dove.cli.api.security.service;

import java.security.NoSuchAlgorithmException;

import com.gustz.dove.cli.api.security.rsp.AccessTokenRsp;
import com.gustz.dove.cli.api.security.rsp.CallbackIpRsp;
import com.gustz.dove.cli.api.service.BaseWebsUrl;

/**
 * 
 * TODO: 接口凭证服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public interface AcTokenService {

    /**
     * 获取当前接入的开发者凭证
     * 
     * <pre> 
     * 状态为S99的客户端APP（唯一）
     * </pre>
     * @return
     * @throws NoSuchAlgorithmException
     */
    String getCurrDevToken() throws NoSuchAlgorithmException;

    /**
     * 获取接口凭证
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    String getAccessToken(BaseWebsUrl websUrl, long sn, String cliAppCode, String devAcCode);

    /**
     * 获取接口凭证VO
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    AccessTokenRsp getAccessTokenVo(BaseWebsUrl websUrl, long sn, String cliAppCode, String devAcCode);

    /**
     * 获取微信服务器IP地址集
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    CallbackIpRsp getCallbackIp(BaseWebsUrl websUrl, long sn, String cliAppCode, String devAcCode);

}