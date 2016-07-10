package com.gustz.dove.mpcli.api.security.service;

import javax.jws.WebService;

import com.gustz.dove.mpcli.api.security.req.AcTokenOAuthReq;
import com.gustz.dove.mpcli.api.security.rsp.AcTokenOAuthRsp;
import com.gustz.dove.mpcli.api.user.req.UserReq;
import com.gustz.dove.mpcli.api.user.rsp.UserRsp;

/**
 * 
 * TODO: OAuth服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
@WebService
public interface OauthMpService {

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
     * 获取Access_Token（oAuth认证,此access_token与基础支持的access_token不同）
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    AcTokenOAuthRsp getOAuthAcToken(long sn, String cliAppCode, AcTokenOAuthReq req);

    /**
     * 通过OAuth获取用户详情
     * 
     * @param sn
     * @param cliAppCode
     * @param oauthAcToken
     * @param req
     * @return
     */
    UserRsp getUserByOAuth(long sn, String cliAppCode, String oauthAcToken, UserReq req);

}