package com.gustz.dove.cpcli.api.service.conf;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;

/**
 * TODO: 菜单服务的URL
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum MenuWsUrl implements BaseWebsUrl {

    /**
     * 创建应用菜单
     */
    WSC05001CP("url", "创建应用菜单"),

    /**
     * 获取菜单列表
     */
    WSC05002CP("url", "获取菜单列表"),

    /**
     * 删除菜单
     */
    WSC05003CP("url", "删除菜单");

    private String url;

    private String text;

    private MenuWsUrl(String url, String text) {
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

class CpMenuWsUrlConf {

    @Value("#{cpMenuWsUrlConf}")
    public void setConf(Properties props) {
        for (Map.Entry<?, ?> _entry : props.entrySet()) {
            MenuWsUrl.valueOf(_entry.getKey().toString()).setUrl(_entry.getValue().toString());
        }
    }
}
