/*
 * @(#)SignServiceImplTest.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.gustz.dove.cli.api.security.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gustz.dove.cli.api.service.base.CliTestBase;

/**
 * TODO: 验证签名服务的接口实现测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 14, 2015 ]
 */
public class SignServiceImplTest extends CliTestBase<String> {

    @Autowired
    private SignServiceImpl signService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link SignServiceImpl#checkSignature(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testCheckMpSign() {
        String signature = "d23d9b704f8f95e64834200cdc2d626abe73c7e9";
        String nonce = "162323307";
        //
        boolean flag = signService.checkMpSign(signature, "1441701517", nonce);
        System.out.println("checkSignature-flag=:" + flag);
        Assert.assertTrue(flag);
    }

    @Test
    public void testCheckCpSign() throws Exception {
        String signature = "389ea1e669092d0aada81ba0a1c098808b9e8756";
        String nonce = "1183368475";
        //
        String retVal = signService.checkCpSign(signature, "1441702850", nonce, "abc");
        System.out.println("checkCpSign-retVal=:" + retVal);
        Assert.assertNotNull(retVal);
    }

}
