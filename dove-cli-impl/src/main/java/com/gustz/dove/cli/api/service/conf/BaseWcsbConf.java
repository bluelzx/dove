package com.gustz.dove.cli.api.service.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * TODO: 基础配置
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Component
public class BaseWcsbConf {

    @Value("#{baseWcsbConf[default_charset]}")
    private String defaultCharset;

    @Value("#{baseWcsbConf[timestamp_limit]}")
    private long timestampLimit;

    /**
     * 默认字符编码UTF-8
     * 
     * @return
     */
    public String getDefaultCharset() {
        return defaultCharset;
    }

    /**
     * 签名有效时间间隔，单位：秒。
     * 
     * <pre>
     * 开发者接入配置时的时间戳
     * </pre>
     * @return
     */
    public long getTimestampLimit() {
        return timestampLimit;
    }

}
