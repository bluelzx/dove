package com.gustz.dove.api.app.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.api.app.service.ClientAppService;
import com.sinovatech.rd.wcsb.api.app.vo.ClientAppVo;
import com.gustz.dove.api.service.base.TestBase;
import com.sinovatech.rd.wcsb.repo.app.po.ClientAppPo;

/**
 * 
 * TODO: 客户端应用服务实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Sep 15, 2015 ]
 */
public class ClientAppServiceImplTest extends TestBase<ClientAppVo, ClientAppPo> {

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

}
