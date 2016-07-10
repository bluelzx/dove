package com.gustz.dove.cpcli.api.service.conf;

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

    // ---------- 接入校验 WSC070 ---------- begin
    /**
     * 获取AccessToken
     */
    WSC07001CP("url", "获取AccessToken"),

    /**
     * 获取微信服务器的IP地址
     */
    WSC07002CP("url", "获取微信服务器的IP地址"),

    // ---------- 接入校验 WSC070 ---------- end
    // ---------- 身份验证 WSC071 ---------- begin

    /**
     * 通过OAuth授权企业获取code
     */
    WSC07101CP("url", "通过OAuth企业获取code"),

    /**
     * 根据code获取成员信息
     */
    WSC07102CP("url", "根据code获取成员信息"),

    /**
     * userid转换成openid接口
     */
    WSC07103CP("url", "userid转换成openid接口"),

    /**
     * openid转换成userid接口
     */
    WSC07104CP("url", "openid转换成userid接口");

    // ---------- 身份验证 WSC071 ---------- end

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

class CpSecurityWsUrlConf {

    @Value("#{cpSecurityWsUrlConf}")
    public void setConf(Properties props) {
        for (Map.Entry<?, ?> _entry : props.entrySet()) {
            SecurityWsUrl.valueOf(_entry.getKey().toString()).setUrl(_entry.getValue().toString());
        }
    }
}
