package com.gustz.dove.cli.api.message.service;

/**
 * 
 * TODO: 消息处理服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 9, 2015 ]
 */
public interface MessageService {

    /**
     * 自动回复消息（客服回复）
     * 
     * <pre>
     * 异步发送客服消息
     * </pre>
     * @param eventType 事件类型
     * @param toUserName 接收者
     * @param fromUserName 发送者
     * @param cliAppCode 客户端APP编码
     * @param content 消息
     */
    void autoReplyMsg(String eventType, String toUserName, String fromUserName, String cliAppCode, String content);

}