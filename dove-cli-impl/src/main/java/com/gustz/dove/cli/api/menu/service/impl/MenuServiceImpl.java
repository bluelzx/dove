package com.gustz.dove.cli.api.menu.service.impl;

import com.gustz.dove.cli.api.service.impl.AbstBaseService;
import com.sinovatech.rd.wcsb.cli.api.menu.req.MenuReq;
import com.sinovatech.rd.wcsb.cli.api.menu.rsp.MenuRsp;
import com.sinovatech.rd.wcsb.cli.api.menu.service.MenuService;
import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;

/**
 * 
 * TODO: 菜单服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public abstract class MenuServiceImpl extends AbstBaseService<MenuReq> implements MenuService {

    /**
     * 创建菜单
     * 
     * @param websUrl
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param req 菜单请求VO 
     * @return
     */
    @Override
    public CommRsp createMenu(BaseWebsUrl websUrl, long sn, String cliAppCode, MenuReq req) {
        // 不校验客户端APP
        this.setCheckCliApp(false);
        //
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(websUrl, sn, cliAppCode, req, new CommRsp(sn, websUrl.getWebsCode()));
    }

    /**
     * 删除菜单
     * 
     * @param websUrl
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param devAcCode
     * @return
     */
    @Override
    public CommRsp delMenu(BaseWebsUrl websUrl, long sn, String cliAppCode, String devAcCode) {
        // 不校验客户端APP
        this.setCheckCliApp(false);
        //
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        //
        return this.httpGet(websUrl, sn, cliAppCode, new CommRsp(sn, websUrl.getWebsCode()));
    }

    /**
     * 查询菜单
     * 
     * @param websUrl
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param devAcCode
     * @return
     */
    @Override
    public MenuRsp getMenu(BaseWebsUrl websUrl, long sn, String cliAppCode, String devAcCode) {
        // 不校验客户端APP
        this.setCheckCliApp(false);
        //
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        //
        return this.httpGet(websUrl, sn, cliAppCode, new MenuRsp(sn, websUrl.getWebsCode()));
    }

}
