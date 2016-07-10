package com.gustz.dove.cpcli.api.service.conf;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;

/**
 * TODO: 通讯录服务的URL
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum AddrbookWsUrl implements BaseWebsUrl {

    // -------------- 用户部门 WSC01 -------------- begin
    /**
     * 创建部门
     */
    WSC01001CP("url", "创建部门"),

    /**
     * 更新部门
     */
    WSC01002CP("url", "更新部门"),

    /**
     * 删除部门
     */
    WSC01003CP("url", "删除部门"),

    /**
     * 获取部门列表
     */
    WSC01004CP("url", "获取部门列表"),

    // -------------- 用户部门 WSC01 -------------- end
    // -------------- 用户 WSC00 -------------- begin

    /**
     * 创建用户
     */
    WSC00001CP("url", "创建用户"),

    /**
     * 更新用户
     */
    WSC00002CP("url", "更新用户"),

    /**
     * 删除用户
     */
    WSC00003CP("url", "删除用户"),

    /**
     * 批量删除用户
     */
    WSC00004CP("url", "批量删除用户"),

    /**
     * 查询用户
     */
    WSC00005CP("url", "查询用户"),

    /**
     * 查询部门下的用户
     */
    WSC00006CP("url", "查询部门下的用户"),

    /**
     * 查询部门下的用户详情
     */
    WSC00007CP("url", "查询部门下的用户详情"),

    /**
     * 邀请成员关注
     */
    WSC00008CP("url", "邀请成员关注");

    // -------------- 用户 WSC00 -------------- end

    private String url;

    private String text;

    private AddrbookWsUrl(String url, String text) {
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

class CpAddrbookWsUrlConf {

    @Value("#{cpAddrbookWsUrlConf}")
    public void setConf(Properties props) {
        for (Map.Entry<?, ?> _entry : props.entrySet()) {
            AddrbookWsUrl.valueOf(_entry.getKey().toString()).setUrl(_entry.getValue().toString());
        }
    }
}
