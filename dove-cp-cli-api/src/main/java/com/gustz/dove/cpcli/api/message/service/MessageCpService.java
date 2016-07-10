package com.gustz.dove.cpcli.api.message.service;

import javax.jws.WebService;

/**
 * 
 * TODO: 消息处理服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 9, 2015 ]
 */
@WebService
public interface MessageCpService {

    /**
     * 转发接收企业号的消息
     * 
     * <pre>
     * 1、直接回复空串（指字节长度为0的空字符串，而不是XML结构体中content字段的内容为空）
     * 2、直接回复success
     * </pre>
     * @param msgSignature
     * @param timestamp
     * @param nonce
     * @param postData
     * @return
     */
    String dispatcherMsg(String msgSignature, String timestamp, String nonce, String postData);

}