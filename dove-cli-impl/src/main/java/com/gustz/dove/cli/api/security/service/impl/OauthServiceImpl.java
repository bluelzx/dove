package com.gustz.dove.cli.api.security.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.gustz.dove.cli.api.app.service.impl.ClientAppService;
import com.gustz.dove.cli.api.app.vo.ClientAppVo;
import com.gustz.dove.cli.api.service.conf.WebsUrlParam;
import com.gustz.dove.cli.api.service.impl.AbstBaseService;
import com.gustz.dove.cli.api.service.util.ClientConstants;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.security.service.OauthService;
import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;
import com.sinovatech.rd.wcsb.cli.api.service.dict.OAuthScopeDict;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;

/**
 * 
 * TODO: OAuth服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public abstract class OauthServiceImpl extends AbstBaseService<AbstBaseReq<?>> implements OauthService {

    @Autowired
    private ClientAppService clientAppService;

    /**
     * 获取snsapi_base范围的OAuth授权URL
     * 
     * @param websUrl
     * @param cliAppCode
     * @param state
     * @return
     */
    @Override
    public String getSnsapiBaseUrl(BaseWebsUrl websUrl, String cliAppCode, String state) {
        try {
            return this.getOauthUrl(websUrl, cliAppCode, OAuthScopeDict.SNSAPI_BASE.getName(), state);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取snsapi_userinfo范围的OAuth授权URL
     * 
     * @param websUrl
     * @param cliAppCode
     * @param state
     * @return
     */
    @Override
    public String getSnsapiUserInfoUrl(BaseWebsUrl websUrl, String cliAppCode, String state) {
        try {
            return this.getOauthUrl(websUrl, cliAppCode, OAuthScopeDict.SNSAPI_USER_INFO.getName(), state);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取OAuth授权URL
     * 
     * @param websUrl
     * @param cliAppCode
     * @param scope
     * @param state
     * @return
     * @throws UnsupportedEncodingException 
     */
    private String getOauthUrl(BaseWebsUrl websUrl, String cliAppCode, String scope, String state)
            throws UnsupportedEncodingException {
        // 获取活动的应用
        ClientAppVo vo = clientAppService.getActiveCliApp().get(cliAppCode);
        if (vo == null) {
            return null;
        }
        // 构建URL
        final String oauthCbUrl = URLEncoder.encode(vo.getOauthCbUrl(), ClientConstants.CHARSET.name())
                + BaseWebsUrl.OAUTH_CBURL_PATT;
        String url = websUrl.getUrl();
        url = url.replace(WebsUrlParam.APP_ID, vo.getWecAppId()) // 公众号唯一标识
                .replace(WebsUrlParam.REDIRECT_URI, oauthCbUrl) // 跳转URL
                .replace(WebsUrlParam.SCOPE, scope) // 应用授权作用域
                .replace(WebsUrlParam.STATE, state); // 重定向后会带上state参数
        //
        return url;
    }

}
