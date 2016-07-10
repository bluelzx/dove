package com.gustz.dove.cpcli.api.security.service;

import javax.jws.WebService;

import com.gustz.dove.cpcli.api.security.rsp.UserOAuthRsp;

/**
 * 
 * TODO: OAuth服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
@WebService
public interface OauthCpService {

    /**
     * 获取snsapi_base范围的OAuth授权URL
     * 
     * @param cliAppCode
     * @param state
     * @return
     */
    String getSnsapiBaseUrl(String cliAppCode, String state);

    /**
     * 获取snsapi_userinfo范围的OAuth授权URL
     * 
     * @param cliAppCode
     * @param state
     * @return
     */
    String getSnsapiUserInfoUrl(String cliAppCode, String state);

    /**
     * 根据code获取成员信息
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param oauthCode
     * @return
     */
    UserOAuthRsp getUserByOAuth(long sn, String cliAppCode, String devAcCode, String oauthCode);

}