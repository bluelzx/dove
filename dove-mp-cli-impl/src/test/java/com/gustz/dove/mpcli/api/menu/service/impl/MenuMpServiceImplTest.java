package com.gustz.dove.mpcli.api.menu.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.menu.req.MenuReq;
import com.sinovatech.rd.wcsb.cli.api.menu.rsp.MenuRsp;
import com.sinovatech.rd.wcsb.cli.api.menu.vo.Button;
import com.sinovatech.rd.wcsb.cli.api.menu.vo.Menu;
import com.sinovatech.rd.wcsb.cli.api.service.dict.BtnTypeDict;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.mpcli.api.menu.service.MenuMpService;
import com.gustz.dove.mpcli.api.service.base.MpCliTestBase;

/**
 * TODO: 菜单服务实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class MenuMpServiceImplTest extends MpCliTestBase<String> {

    @Autowired
    private MenuMpService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link MenuMpServiceImpl#createMenu(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.menu.req.MenuReq)}.
     */
    @Test
    public void testCreateMenu() {
        Button subBtn1 = new Button(BtnTypeDict.VIEW, "填写日报", "http://124.192.56.203/dayrpt-web/wap/logBook/editLogBook.do");
        Button subBtn2 = new Button(BtnTypeDict.VIEW, "查看日报", "http://www.baidu.com");
        Button btn = new Button("销售日报", new Button[] { subBtn1, subBtn2 });
        //
        Button btn2 = new Button(BtnTypeDict.VIEW, "统计分析", "http://www.baidu.com");
        Button btn3 = new Button(BtnTypeDict.VIEW, "通讯录", "http://124.192.56.203/wcsb-cli-demo/addrlist/listAddr");
        //
        MenuReq req = new MenuReq(devAcCode, new Menu(new Button[] { btn, btn2, btn3 }));
        //
        CommRsp _rsp = service.createMenu(sn, cliAppCode, req);
        //
        System.out.println("createMenu-rsp=:" + _rsp.getBody().getErrMsg());
        Assert.assertEquals(0, _rsp.getBody().getErrCode());
        Assert.assertNotNull(_rsp.getBody().getErrMsg());
    }

    /**
     * Test method for {@link MenuMpServiceImpl#getMenu(long, java.lang.String)}.
     */
    @Test
    public void testGetMenu() {
        MenuRsp _rsp = service.getMenu(sn, cliAppCode, devAcCode);
        //
        System.out.println("getMenu-rsp=:" + _rsp.getBody().getMenu());
        Assert.assertEquals(0, _rsp.getBody().getErrCode());
        Assert.assertNotNull(_rsp.getBody().getMenu());
    }

    @Test
    public void testDelMenu() {
        CommRsp _rsp = service.delMenu(sn, cliAppCode, devAcCode);
        //
        System.out.println("delMenu-rsp=:" + _rsp.getBody());
        Assert.assertEquals(0, _rsp.getBody().getErrCode());
        Assert.assertNotNull(_rsp.getBody());
    }

}
