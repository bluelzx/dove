package com.gustz.dove.cpcli.api.service.conf;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;

/**
 * TODO: 代理商（企业号应用）服务的URL
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum AgentWsUrl implements BaseWebsUrl {

    /**
     * 获取企业号应用
     */
    WSC02001CP("url", "获取企业号应用"),

    /**
     * 设置企业号应用
     */
    WSC02002CP("url", "设置企业号应用"),

    /**
     * 获取应用概况列表
     */
    WSC02003CP("url", "获取应用概况列表");

    private String url;

    private String text;

    private AgentWsUrl(String url, String text) {
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

class CpAgentWsUrlConf {

    @Value("#{cpAgentWsUrlConf}")
    public void setConf(Properties props) {
        for (Map.Entry<?, ?> _entry : props.entrySet()) {
            AgentWsUrl.valueOf(_entry.getKey().toString()).setUrl(_entry.getValue().toString());
        }
    }
}