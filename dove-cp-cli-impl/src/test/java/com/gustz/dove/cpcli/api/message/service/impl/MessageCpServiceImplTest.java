package com.gustz.dove.cpcli.api.message.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gustz.dove.cpcli.api.service.base.CpCliTestBase;

/**
 * TODO: 消息处理服务的接口测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 14, 2015 ]
 */
public class MessageCpServiceImplTest extends CpCliTestBase<String> {

    @Autowired
    private MessageCpServiceImpl service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cpcli.api.message.service.impl.MessageServiceImpl#dispatcherMsg(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testDispatcherMsg() {
        String msgSignature = null;
        String timestamp = null;
        String nonce = null;
        String postData = null;
        String retVal = service.dispatcherMsg(msgSignature, timestamp, nonce, postData);
        System.out.println("dispatcherMsg-retVal=:" + retVal);
    }

}
