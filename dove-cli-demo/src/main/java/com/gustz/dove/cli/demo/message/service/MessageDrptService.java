package com.sinovatech.rd.wcsb.cli.demo.message.service;

/**
 * TODO: 消息服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public interface MessageDrptService {

    /**
     * 处理消息
     * 
     * @param msgReq
     */
    void handleMsg(final String msgReq);
}
