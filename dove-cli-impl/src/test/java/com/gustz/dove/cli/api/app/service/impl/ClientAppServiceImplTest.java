package com.gustz.dove.cli.api.app.service.impl;

import java.util.Map;

import com.gustz.dove.cli.api.service.base.CliTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gustz.dove.cli.api.app.vo.ClientAppVo;

/**
 * 
 * TODO: 客户端应用服务实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Sep 15, 2015 ]
 */
public class ClientAppServiceImplTest extends CliTestBase<ClientAppVo> {

    @Autowired
    private ClientAppService clientAppService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testGetActiveCliAppString() {
    }

    @Test
    public void testGetCurrCliApp() {
    }

    @Test
    public void testGetActiveCache() {
    }

    @Test
    public void testEnableCliApp() {
    }

    @Test
    public void testChgStatus() {
    }

    @Test
    public void testChgDevStatus() {
    }

    @Test
    public void testGetActiveCliApp() {
        //
        Map<String, ClientAppVo> _map = clientAppService.getActiveCliApp();
        System.out.println("getActiveCliApp-map=:" + _map);
        // 
        Assert.assertNotEquals(0, _map.size());
    }

    @Test
    public void testCheckCliApp() {
        ClientAppVo vo = new ClientAppVo();
        vo.setCliAppCode("oa-dayrpt");
        vo.setCliAppPwd("819694e2a6f62de261bba18be42c4b2f");
        vo.setAccountCode("2_wx4cd30499c762f181");
        vo.setWebsCodes("WSC04001");
        vo.setCliIpAddrsIn(new String[] { "127.0.0.1", "127.0.0.2" });
        //
        clientAppService.checkCliApp(vo);
    }

}
