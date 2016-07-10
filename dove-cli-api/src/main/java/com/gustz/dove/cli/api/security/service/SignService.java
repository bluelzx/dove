package com.gustz.dove.cli.api.security.service;

import javax.jws.WebService;

/**
 * 
 * TODO: 验证签名服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
@WebService
public interface SignService {

    /**
     * Check signature
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    boolean checkMpSign(String signature, String timestamp, String nonce);

    /**
     * 校验URL并返回结果
     * 
     * @param msgSignature
     * @param timestamp
     * @param nonce
     * @param echoStr
     * @return
     * @throws Exception
     */
    String checkCpSign(String msgSignature, String timestamp, String nonce, String echoStr) throws Exception;

}