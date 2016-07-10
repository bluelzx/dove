package com.gustz.dove.mpcli.api.message.service.impl;

import com.gustz.dove.mpcli.api.service.base.MpCliTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO: 消息处理服务的接口测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 14, 2015 ]
 */
public class MessageMpServiceImplTest extends MpCliTestBase<String> {

    @Autowired
    private MessageMpServiceImpl service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cli.api.message.service.impl.MessageServiceImpl#dispatcherMsg(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testDispatcherMsg() {
        String encryptType = null;
        String msgSignature = null;
        String timestamp = null;
        String nonce = null;
        String postData = null;
        String retVal = service.dispatcherMsg(encryptType, msgSignature, timestamp, nonce, postData);
        System.out.println("dispatcherMsg-retVal=:" + retVal);
    }

}
