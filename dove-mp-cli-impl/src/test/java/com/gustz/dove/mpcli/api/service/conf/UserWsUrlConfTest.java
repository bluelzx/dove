package com.gustz.dove.mpcli.api.service.conf;

import org.junit.Assert;
import org.junit.Test;

import com.gustz.dove.mpcli.api.service.base.MpCliTestBase;

/**
 * TODO: 用户和群组服务的URL配置测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 5, 2015 ]
 */
public class UserWsUrlConfTest extends MpCliTestBase<String> {

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cli.api.user.service.conf.UserWebsUrl#getUrl()}.
     */
    @Test
    public void testGetUrl() {
        String _url = UserWsUrl.WSC00001MP.getUrl();
        System.out.println("getUrl-url=:" + _url);
        Assert.assertNotNull(_url);
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cli.api.user.service.conf.UserWebsUrl#getText()}.
     */
    @Test
    public void testGetText() {
        String _text = UserWsUrl.WSC00002MP.getText();
        System.out.println("getText-text=:" + _text);
        Assert.assertNotNull(_text);
    }

}
