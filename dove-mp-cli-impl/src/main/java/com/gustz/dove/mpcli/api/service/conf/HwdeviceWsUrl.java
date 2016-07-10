package com.gustz.dove.mpcli.api.service.conf;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;

/**
 * TODO: 硬件设备服务的URL
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum HwdeviceWsUrl implements BaseWebsUrl {

    // ---------------- 硬件设备基础 WSC080 ---------------- begin
    /**
     * 生成设备二维码
     */
    WSC08001MP("url", "生成设备二维码"),

    /**
     * 设备授权
     */
    WSC08002MP("url", "设备授权"),

    /**
     * 获取设备ID和二维码（新接口）
     */
    WSC08003MP("url", "获取设备ID和二维码（新接口）"),

    /**
     * 绑定用户和设备
     */
    WSC08004MP("url", "绑定用户和设备"),

    /**
     * 解绑用户和设备
     */
    WSC08005MP("url", "解绑用户和设备"),

    /**
     * 强制绑定用户和设备
     */
    WSC08006MP("url", "强制绑定用户和设备"),

    /**
     * 强制解绑用户和设备
     */
    WSC08007MP("url", "强制解绑用户和设备"),

    /**
     * 查询设备状态
     */
    WSC08008MP("url", "查询设备状态"),

    /**
     * 验证设备二维码
     */
    WSC08009MP("url", "验证设备二维码"),

    /**
     * 获取设备绑定的OpenID
     */
    WSC08010MP("url", "获取设备绑定的OpenID"),

    /**
     * 获取用户绑定的设备
     */
    WSC08011MP("url", "获取用户绑定的设备");

    // ---------------- 硬件设备基础 WSC080 ---------------- end

    private String url;

    private String text;

    private HwdeviceWsUrl(String url, String text) {
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

class MpHwdeviceWsUrlConf {

    @Value("#{mpHwdeviceWsUrlConf}")
    public void setConf(Properties props) {
        for (Map.Entry<?, ?> _entry : props.entrySet()) {
            HwdeviceWsUrl.valueOf(_entry.getKey().toString()).setUrl(_entry.getValue().toString());
        }
    }
}