package com.gustz.dove.cpcli.api.menu.service.impl;

import com.gustz.dove.cpcli.api.service.base.CpCliTestBase;
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
import com.sinovatech.rd.wcsb.cpcli.api.menu.service.MenuCpService;

/**
 * TODO: 菜单服务实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class MenuCpServiceImplTest extends CpCliTestBase<String> {

    @Autowired
    private MenuCpService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cpcli.api.menu.service.impl.MenuServiceImpl#createMenu(long, java.lang.String, com.sinovatech.rd.wcsb.cpcli.api.menu.req.MenuReq)}.
     * @throws Exception 
     */
    @Test
    public void testCreateMenu() throws Exception {
        Button subBtn1 = new Button(BtnTypeDict.VIEW, "填写日报", "http://124.192.56.203/dayrpt-web/wap/logBook/editLogBook.do");
        Button subBtn2 = new Button(BtnTypeDict.VIEW, "补交日报", "http://124.192.56.203/dayrpt-web/wap/logBook/listRewLogBook.do");
        Button subBtn3 = new Button(BtnTypeDict.VIEW, "查看日报", "http://124.192.56.203/dayrpt-web/wap/logBook/viewLogBook.do");
        Button btn = new Button(BtnTypeDict.CLICK, "销售日报", "", new Button[] { subBtn1, subBtn2, subBtn3 });
        //
        Button btn2 = new Button(BtnTypeDict.VIEW, "日报统计", "http://124.192.56.203/dayrpt-web/wap/logBook/listStatLogBook.do");
        Button btn3 = new Button(BtnTypeDict.VIEW, "通讯录", "http://124.192.56.203/dayrpt-web/wap/addrBook/listAddrBook.do");
        MenuReq req = new MenuReq(devAcCode, new Menu(new Button[] { btn, btn2, btn3 }));

        //System.out.println(JsonMapper.writeValueAsString(false, req));

        // oa-dayrpt
        CommRsp _rsp = service.createMenu(sn, cliAppCode, req);
        //
        System.out.println("createMenu-rsp=:" + _rsp.getBody().getErrMsg());
        Assert.assertEquals(0, _rsp.getBody().getErrCode());
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cpcli.api.menu.service.impl.MenuServiceImpl#getMenu(long, java.lang.String)}.
     */
    @Test
    public void testGetMenu() {
        MenuRsp _rsp = service.getMenu(sn, cliAppCode, devAcCode);
        //
        System.out.println("getMenu-rsp=:" + _rsp.getBody().getMenu());
        System.out.println("getMenu-errmsg=:" + _rsp.getBody().getErrMsg());
        Assert.assertEquals(0, _rsp.getBody().getErrCode());
    }

    @Test
    public void testDelMenu() {
        CommRsp _rsp = service.delMenu(sn, cliAppCode, devAcCode);
        //
        System.out.println("delMenu-rsp=:" + _rsp.getBody().getErrMsg());
        Assert.assertEquals(0, _rsp.getBody().getErrCode());
    }

}
