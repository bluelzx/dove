package com.gustz.dove.cpcli.api.security.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.security.service.impl.OauthServiceImpl;
import com.sinovatech.rd.wcsb.cli.api.service.conf.WebsUrlParam;
import com.sinovatech.rd.wcsb.cpcli.api.security.rsp.UserOAuthRsp;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.AcTokenCpService;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.OauthCpService;
import com.gustz.dove.cpcli.api.service.conf.SecurityWsUrl;

/**
 * 
 * TODO: OAuth服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class OauthCpServiceImpl extends OauthServiceImpl implements OauthCpService {

    @Autowired
    private AcTokenCpService acTokenCpService;

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
        return this.getSnsapiBaseUrl(SecurityWsUrl.WSC07101CP, cliAppCode, state);
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
        return this.getSnsapiUserInfoUrl(SecurityWsUrl.WSC07101CP, cliAppCode, state);
    }

    /**
     * 根据code获取成员信息
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param oauthCode
     * @return
     */
    @Override
    public UserOAuthRsp getUserByOAuth(long sn, String cliAppCode, String devAcCode, String oauthCode) {
        SecurityWsUrl _wsUrl = SecurityWsUrl.WSC07102CP;
        // 请求参数
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        Map<String, String> _paramMap = new HashMap<String, String>();
        _paramMap.put(WebsUrlParam.CODE, oauthCode); // OAuth授权的code
        this.setWebsUrlParamMap(_paramMap);
        // 
        return this.httpGet(_wsUrl, sn, cliAppCode, new UserOAuthRsp(sn, _wsUrl.getWebsCode()));
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenCpService.getAccessTokenCp(sn, cliAppCode, devAcCode));
    }

}
