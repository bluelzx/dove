package com.gustz.dove.cli.api.service;

import java.io.Serializable;

/**
 * 
 * TODO: Base service
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 4, 2015 ]
 */
public interface BaseService<REQ extends Serializable> {

    /**
     * Post for upload file
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode client appCode
     * @param reqVo
     * @param rspVo 
     * @return
     */
    <T> T uploadFile(BaseWebsUrl websUrl, long sn, String cliAppCode, REQ reqVo, T rspVo);

    /**
     * Get for download file
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode client appCode
     * @param reqVo
     * @param rspVo 
     * @return
     */
    <T> T downloadFile(BaseWebsUrl websUrl, long sn, String cliAppCode, REQ reqVo, T rspVo);

    /**
     * HTTP post request
     * 
     * @param websUrl
     * @param cliAppCode client appCode
     * @param reqVo
     * @param rspVo
     * @return
     */
    <T> T httpPost(BaseWebsUrl websUrl, String cliAppCode, REQ reqVo, T rspVo);

    /**
     * HTTP post request
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode client appCode
     * @param reqVo
     * @param rspVo
     * @return
     */
    <T> T httpPost(BaseWebsUrl websUrl, long sn, String cliAppCode, REQ reqVo, T rspVo);

    /**
     * HTTP post request
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode
     * @param content
     * @return
     */
    String httpPost(String websUrl, long sn, String cliAppCode, String content);

    /**
     * HTTP get request
     * 
     * @param websUrl
     * @param cliAppCode client appCode
     * @param rspVo
     * @return
     */
    <T> T httpGet(BaseWebsUrl websUrl, String cliAppCode, T rspVo);

    /**
     * HTTP get request
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode client appCode
     * @param rspVo
     * @return
     */
    <T> T httpGet(BaseWebsUrl websUrl, long sn, String cliAppCode, T rspVo);

    /**
     * Create cache
     * 
     * @return
     */
    CacheService<?, ?> createCache();

}