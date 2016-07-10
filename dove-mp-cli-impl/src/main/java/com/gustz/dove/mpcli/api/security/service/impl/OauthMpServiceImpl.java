package com.gustz.dove.mpcli.api.security.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.security.service.impl.OauthServiceImpl;
import com.sinovatech.rd.wcsb.cli.api.service.conf.WebsUrlParam;
import com.sinovatech.rd.wcsb.mpcli.api.security.req.AcTokenOAuthReq;
import com.sinovatech.rd.wcsb.mpcli.api.security.rsp.AcTokenOAuthRsp;
import com.sinovatech.rd.wcsb.mpcli.api.security.service.OauthMpService;
import com.gustz.dove.mpcli.api.service.conf.SecurityWsUrl;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.rsp.UserRsp;

/**
 * 
 * TODO: OAuth服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class OauthMpServiceImpl extends OauthServiceImpl implements OauthMpService {

    /**
     * 获取snsapi_base范围的OAuth授权URL
     * 
     * @param cliAppCode
     * @param state
     * @return
     */
    @Override
    public String getSnsapiBaseUrl(String cliAppCode, String state) {
        //
        return this.getSnsapiBaseUrl(SecurityWsUrl.WSC02103MP, cliAppCode, state);
    }

    /**
     * 获取snsapi_userinfo范围的OAuth授权URL
     * 
     * @param cliAppCode
     * @param state
     * @return
     */
    @Override
    public String getSnsapiUserInfoUrl(String cliAppCode, String state) {
        //
        return this.getSnsapiUserInfoUrl(SecurityWsUrl.WSC02103MP, cliAppCode, state);
    }

    /**
     * 获取Access_Token（oAuth认证,此access_token与基础支持的access_token不同）
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public AcTokenOAuthRsp getOAuthAcToken(long sn, String cliAppCode, AcTokenOAuthReq req) {
        SecurityWsUrl _wsUrl = SecurityWsUrl.WSC02102MP;
        // 请求参数
        Map<String, String> _paramMap = new HashMap<String, String>();
        _paramMap.put(WebsUrlParam.APP_ID, req.getBody().getAppId()); // 公众号唯一标识
        _paramMap.put(WebsUrlParam.APP_SECRET, req.getBody().getAppSecret());
        _paramMap.put(WebsUrlParam.CODE, req.getBody().getCode());
        this.setWebsUrlParamMap(_paramMap);
        // do 
        return this.httpGet(_wsUrl, sn, cliAppCode, new AcTokenOAuthRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 通过OAuth获取用户详情
     * 
     * @param sn
     * @param cliAppCode
     * @param oauthAcToken
     * @param req
     * @return
     */
    @Override
    public UserRsp getUserByOAuth(long sn, String cliAppCode, String oauthAcToken, UserReq req) {
        SecurityWsUrl _wsUrl = SecurityWsUrl.WSC02101MP;
        // 请求参数
        Map<String, String> _paramMap = new HashMap<String, String>();
        _paramMap.put(WebsUrlParam.ACCESS_TOKEN, oauthAcToken); // OAuth凭证
        _paramMap.put(WebsUrlParam.OPEN_ID, req.getBody().getOpenId()); // 公众号
        _paramMap.put(WebsUrlParam.LANG, req.getBody().getLang());
        this.setWebsUrlParamMap(_paramMap);
        // 
        return this.httpGet(_wsUrl, sn, cliAppCode, new UserRsp(sn, _wsUrl.getWebsCode()));
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        // ignored
    }

}
