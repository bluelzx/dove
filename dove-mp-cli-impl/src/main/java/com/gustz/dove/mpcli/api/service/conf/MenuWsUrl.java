package com.gustz.dove.mpcli.api.service.conf;

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
     * 创建菜单（POST） 限100（次/天）
     */
    WSC04001MP("url", "创建菜单"),

    /**
     * 查询菜单
     */
    WSC04002MP("url", "查询菜单"),

    /**
     * 删除菜单
     */
    WSC04003MP("url", "删除菜单");

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

class MpMenuWsUrlConf {

    @Value("#{mpMenuWsUrlConf}")
    public void setConf(Properties props) {
        for (Map.Entry<?, ?> _entry : props.entrySet()) {
            MenuWsUrl.valueOf(_entry.getKey().toString()).setUrl(_entry.getValue().toString());
        }
    }
}
