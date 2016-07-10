package com.gustz.dove.cpcli.api.service.conf;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;

/**
 * TODO: 消息服务的URL
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum MessageWsUrl implements BaseWebsUrl {

    @Deprecated
    WSC03001("", "");

    private String url;

    private String text;

    private MessageWsUrl(String url, String text) {
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

class CpMessageWsUrlConf {

    @Value("#{cpMessageWsUrlConf}")
    public void setConf(Properties props) {
        for (Map.Entry<?, ?> _entry : props.entrySet()) {
            MessageWsUrl.valueOf(_entry.getKey().toString()).setUrl(_entry.getValue().toString());
        }
    }
}