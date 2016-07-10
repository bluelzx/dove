package com.gustz.dove.mpcli.api.service.conf;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;

/**
 * TODO: 用户和群组服务的URL
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum UserWsUrl implements BaseWebsUrl {

    // -------------- 用户 WSC00 -------------- begin
    /**
     * 获取用户详情
     */
    WSC00001MP("url", "获取用户详情"),

    /**
     * 获取用户openId列表
     */
    WSC00002MP("url", "获取用户openId列表"),

    /**
     * 批量获取用户基本信息
     */
    WSC00003MP("url", "批量获取用户基本信息"),

    // -------------- 用户 WSC00 -------------- end
    // -------------- 用户群组 WSC01 -------------- begin

    /**
     * 创建群组
     */
    WSC01001MP("url", "创建群组"),

    /**
     * 查询全部群组
     */
    WSC01002MP("url", "查询全部群组"),

    /**
     * 查询用户所在的群组
     */
    WSC01003MP("url", "查询用户所在的群组"),

    /**
     * 更新群组
     */
    WSC01004MP("url", "更新群组"),

    /**
     * 更新群组的用户
     */
    WSC01005MP("url", "更新群组的用户");

    // -------------- 用户群组 WSC01 -------------- end

    private String url;

    private String text;

    private UserWsUrl(String url, String text) {
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

class MpUserWsUrlConf {

    @Value("#{mpUserWsUrlConf}")
    public void setConf(Properties props) {
        for (Map.Entry<?, ?> _entry : props.entrySet()) {
            UserWsUrl.valueOf(_entry.getKey().toString()).setUrl(_entry.getValue().toString());
        }
    }
}
