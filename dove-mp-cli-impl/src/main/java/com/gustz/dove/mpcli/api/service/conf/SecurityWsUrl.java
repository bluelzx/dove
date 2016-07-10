package com.gustz.dove.mpcli.api.service.conf;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;

/**
 * TODO: 安全服务的URL
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum SecurityWsUrl implements BaseWebsUrl {

    // ---------- 接入校验 WSC020 ---------- begin
    /**
     * 获取access_token的接口地址（GET） 限200（次/天）
     */
    WSC02001MP("url", "获取访问令牌"),

    /**
     * 获取微信服务器IP地址集
     */
    WSC02002MP("url", "获取微信服务器IP地址集"),

    // ---------- 接入校验 WSC020 ---------- end
    // ---------- 身份验证 WSC021 ---------- begin

    /**
     * 通过OAuth获取用户详情
     */
    WSC02101MP("url", "通过OAuth获取用户详情"),

    /**
     * 获取OAuth网页认证的token
     */
    WSC02102MP("url", "获取OAuth网页认证token"),

    /**
     * OAuth授权
     */
    WSC02103MP("url", "OAuth授权");

    // ---------- 身份验证 WSC021 ---------- end

    private String url;

    private String text;

    private SecurityWsUrl(String url, String text) {
        this.url = url;
        this.text = text;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getWebsCode() {
        return this.name();
    }
}

class MpSecurityWsUrlConf {

    @Value("#{mpSecurityWsUrlConf}")
    public void setConf(Properties props) {
        for (Map.Entry<?, ?> _entry : props.entrySet()) {
            SecurityWsUrl.valueOf(_entry.getKey().toString()).setUrl(_entry.getValue().toString());
        }
    }
}
