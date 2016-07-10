package com.gustz.dove.cpcli.api.service.conf;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;

/**
 * TODO: 素材服务的URL
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum MaterialWsUrl implements BaseWebsUrl {

    /**
     * 上传临时素材文件
     */
    WSC04001CP("url", "上传临时素材文件"),

    /**
     * 获取临时素材文件
     */
    WSC04002CP("url", "获取临时素材文件"),

    /**
     * 上传永久素材
     */
    WSC04003CP("url", "上传永久素材"),

    /**
     * 获取永久素材
     */
    WSC04004CP("url", "获取永久素材"),

    /**
     * 删除永久素材
     */
    WSC04005CP("url", "删除永久素材"),

    /**
     * 修改永久图文素材
     */
    WSC04006CP("url", "修改永久图文素材"),

    /**
     * 获取素材总数
     */
    WSC04007CP("url", "获取素材总数"),

    /**
     * 获取素材列表
     */
    WSC04008CP("url", "获取素材列表");

    private String url;

    private String text;

    private MaterialWsUrl(String url, String text) {
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

class CpMaterialWsUrlConf {

    @Value("#{cpMaterialWsUrlConf}")
    public void setConf(Properties props) {
        for (Map.Entry<?, ?> _entry : props.entrySet()) {
            MaterialWsUrl.valueOf(_entry.getKey().toString()).setUrl(_entry.getValue().toString());
        }
    }
}
