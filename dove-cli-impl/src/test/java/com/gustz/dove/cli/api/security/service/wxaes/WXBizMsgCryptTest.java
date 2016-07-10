/*
 * @(#)WXBizMsgCryptTest.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.gustz.dove.cli.api.security.service.wxaes;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.gustz.dove.cli.api.service.base.CliTestBase;

/**
 * TODO: 微信加解密消息的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 13, 2015 ]
 */
public class WXBizMsgCryptTest extends CliTestBase<String> {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link WXBizMsgCrypt#encryptMsg(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testEncryptMsg() {
    }

    /**
     * Test method for {@link WXBizMsgCrypt#decryptMsg(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testDecryptMsg() {
    }

    /**
     * Test method for {@link WXBizMsgCrypt#verifyUrl(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testVerifyUrl() {
    }

    public static void main(String[] args) throws Exception {
        //
        // 第三方回复公众平台
        //
        // 需要加密的明文
        String encodingAesKey = "991e774ec250251467eebfb9c9fd52d240e71d48000";
        String token = "d9122943d962713f08e94fba93da63be";
        String timestamp = "1441718147";
        String nonce = "1780523614";
        String appId = "wx4cd30499c762f181";
        String replyMsg = " 中文<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";

        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
        String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
        System.out.println("加密后: \n" + mingwen);
        //
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(mingwen);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);
        //
        Element root = document.getDocumentElement();
        NodeList nodelist1 = root.getElementsByTagName("Encrypt");
        NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

        String encrypt = nodelist1.item(0).getTextContent();
        String msgSignature = nodelist2.item(0).getTextContent();

        //
        // 公众平台发送消息给第三方，第三方处理
        //


        // 第三方收到公众号平台发送的消息
        String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, encrypt);
        System.out.println("解密后明文: " + result2);

        //pc.verifyUrl(null, null, null, null);
    }

}
