package com.gustz.dove.cli.api.security.service;

import javax.jws.WebService;

/**
 * 
 * TODO: 加解密服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 13, 2015 ]
 */
@WebService
public interface EncryptService {

    /**
     * 获取开发者密钥AES
     * 
     * @param cliAppCode 客户端APP编码
     * @param devAcCode 开发者号
     * @param wecAppId 微信APP ID
     * @return 
     */
    String getDevAesKeyt(String cliAppCode, String devAcCode, String wecAppId);

    /**
     * 获取开发者token
     * 
     * @param devAcCode 开发者号
     * @param wecAppId 微信APP ID
     * @return 
     */
    String getDevToken(String devAcCode, String wecAppId);

    /**
     * 加密客户端APP数据
     * 
     * @param cliAppCode 客户端APP编码
     * @param userAcCode 账号/微信号
     * @param deviceCode 设备编码（手机设备）
     * @param data 明文数据
     * @return 密文数据
     */
    String encryptCliAppData(String cliAppCode, String userAcCode, String deviceCode, String data);

    /**
     * 解密客户端APP数据
     * 
     * @param cliAppCode 客户端APP编码
     * @param userAcCode 账号/微信号
     * @param deviceCode 设备编码（手机设备）
     * @param encryptData 密文数据
     * @return 明文数据
     */
    String decryptCliAppData(String cliAppCode, String userAcCode, String deviceCode, String encryptData);

}