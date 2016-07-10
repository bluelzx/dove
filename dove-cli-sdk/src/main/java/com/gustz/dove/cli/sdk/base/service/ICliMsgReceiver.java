package com.gustz.dove.cli.sdk.base.service;

/**
 * TODO: 客户消息接收服务I
 *
 * @author ZHENFENG ZHANG
 * @since [ Oct 26, 2015 ]
 */
public interface ICliMsgReceiver {

    /**
     * 执行接收消息
     * 
     * @param msg
     */
    void doMsgReceiver(String msg);

}
