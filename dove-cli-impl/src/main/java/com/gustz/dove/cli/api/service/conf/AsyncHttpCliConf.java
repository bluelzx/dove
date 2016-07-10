package com.gustz.dove.cli.api.service.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * TODO: Async http client配置
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Component
public class AsyncHttpCliConf {

    /** 
     * 连接超时时间 20s
     */
    @Value("#{asyncHttpCliConf[connTimeoutMs]}")
    private int connTimeoutMs = 20000;

    /** 
     * 连接请求超时时间 10s
     */
    @Value("#{asyncHttpCliConf[connRequestTimeoutMs]}")
    private int connRequestTimeoutMs = 10000;

    /** 
     * 读取数据超时时间 300s
     */
    @Value("#{asyncHttpCliConf[soTimeoutMs]}")
    private int soTimeoutMs = 300000;

    /**
     * max number of connections allowed,default: 4000.
     */
    @Value("#{asyncHttpCliConf[maxTotal]}")
    private int maxTotal = 4000;

    /**
     * max number of connections allowed per route,default: 200.
     */
    @Value("#{asyncHttpCliConf[maxPerRoute]}")
    private int maxPerRoute = 200;

    /**
     * 连接超时时间
     * 
     * @return
     */
    public int getConnTimeoutMs() {
        return connTimeoutMs;
    }

    /** 
     * 读取数据超时时间
     */
    public int getSoTimeoutMs() {
        return soTimeoutMs;
    }

    /**
     * 最大连接数
     * 
     * @return
     */
    public int getMaxTotal() {
        return maxTotal;
    }

    /**
     * 每个路由最大连接数
     * 
     * @return
     */
    public int getMaxPerRoute() {
        return maxPerRoute;
    }

    /**
     * 连接请求超时时间
     * 
     * @return
     */
    public int getConnRequestTimeoutMs() {
        return connRequestTimeoutMs;
    }

}
