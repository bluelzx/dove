package com.gustz.dove.mpcli.api.service.conf;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;

/**
 * TODO: 账号服务的URL
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum AccountWsUrl implements BaseWebsUrl {

    // ---------------- 二维码WSC07 ---------------- begin
    /**
     * 生成二维码
     */
    WSC07001MP("url", "生成二维码"),

    /**
     * 获取二维码图片
     */
    WSC07002MP("url", "获取二维码图片");

    // ---------------- 二维码WSC07 ---------------- end

    private String url;

    private String text;

    private AccountWsUrl(String url, String text) {
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

class MpAccountWsUrlConf {

    @Value("#{mpAccountWsUrlConf}")
    public void setConf(Properties props) {
        for (Map.Entry<?, ?> _entry : props.entrySet()) {
            AccountWsUrl.valueOf(_entry.getKey().toString()).setUrl(_entry.getValue().toString());
        }
    }
}