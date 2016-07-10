package com.gustz.dove.cli.api.security.service;

import com.gustz.dove.cli.api.service.BaseWebsUrl;

/**
 * 
 * TODO: OAuth服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
public interface OauthService {

    /**
     * 获取snsapi_base范围的OAuth授权URL
     * 
     * @param websUrl
     * @param cliAppCode
     * @param state
     * @return
     */
    String getSnsapiBaseUrl(BaseWebsUrl websUrl, String cliAppCode, String state);

    /**
     * 获取snsapi_userinfo范围的OAuth授权URL
     * 
     * @param websUrl
     * @param cliAppCode
     * @param state
     * @return
     */
    String getSnsapiUserInfoUrl(BaseWebsUrl websUrl, String cliAppCode, String state);

}