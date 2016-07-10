/*
 * @(#)EncryptServiceImplTest.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.gustz.dove.cli.api.security.service.impl;

import java.security.NoSuchAlgorithmException;

import com.gustz.dove.cli.api.service.base.CliTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.security.service.EncryptService;

/**
 * TODO: 加密服务接口实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 13, 2015 ]
 */
public class EncryptServiceImplTest extends CliTestBase<String> {

    @Autowired
    private EncryptService encryptService;

    private String testData = "你好";

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testGetDevAesKeyt() throws NoSuchAlgorithmException {
        String cliAppCode = "oa-dayrpt";
        String devAcCode = "2";
        String wecAppId = "wx4cd30499c762f181";
        String _keyt = encryptService.getDevAesKeyt(cliAppCode, devAcCode, wecAppId);
        // 991e774ec250251467eebfb9c9fd52d240e71d48000
        System.out.println("getDevAesKeyt-keyt=:" + _keyt);
        System.out.println("getDevAesKeyt-keyt-len=:" + _keyt.length());
        Assert.assertNotEquals(0, _keyt.length());
    }

    @Test
    public void testGetDevToken() throws NoSuchAlgorithmException {
        String devAcCode = "2";
        String wecAppId = "wx4cd30499c762f181";
        String _keyt = encryptService.getDevToken(devAcCode, wecAppId);
        // d9122943d962713f08e94fba93da63be
        System.out.println("getDevToken-keyt=:" + _keyt);
        System.out.println("getDevAesKeyt-keyt-len=:" + _keyt.length());
        Assert.assertNotEquals(0, _keyt.length());
    }

    @Test
    public void testEncryptCliAppData() throws Exception {
        String _data = encryptService.encryptCliAppData(cliAppCode, devAcCode, "tlx$40614e7103d1d411f0cb63e01fbaaf5a", testData);
        //
        System.out.println("encryptCliAppData-data=:" + _data);
        System.out.println("encryptCliAppData-data-len=:" + _data.length());
        Assert.assertNotEquals(0, _data.length());
    }

    @Test
    public void testDecryptCliAppData() throws Exception {
        String encryptData = encryptService.encryptCliAppData(cliAppCode, devAcCode, "tlx$40614e7103d1d411f0cb63e01fbaaf5a",
                testData);
        // cliAppCode = "oa-dayrpt";
        // devAcCode = "13426472281";
        //
        String _data = encryptService.decryptCliAppData(cliAppCode, devAcCode, "abc", encryptData);
        //
        System.out.println("decryptCliAppData-data=:" + _data);
        System.out.println("decryptCliAppData-data-len=:" + _data.length());
        Assert.assertEquals(testData, _data);
    }

}
