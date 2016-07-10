package com.gustz.dove.cpcli.api.service.conf;

import org.junit.Assert;
import org.junit.Test;

import com.gustz.dove.cpcli.api.service.base.CpCliTestBase;

/**
 * TODO: 通讯录服务的URL配置测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 5, 2015 ]
 */
public class AddrbookWsUrlTest extends CpCliTestBase<String> {

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cli.api.user.service.conf.UserWebsUrl#getUrl()}.
     */
    @Test
    public void testGetUrl() {
        String _url = AddrbookWsUrl.WSC01001CP.getUrl();
        System.out.println("getUrl-url=:" + _url);
        Assert.assertNotNull(_url);
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cli.api.user.service.conf.UserWebsUrl#getText()}.
     */
    @Test
    public void testGetText() {
        String _text = AddrbookWsUrl.WSC01001CP.getText();
        System.out.println("getText-text=:" + _text);
        Assert.assertNotNull(_text);
    }

}
