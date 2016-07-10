package com.gustz.dove.mpcli.api.menu.service.impl;

import com.gustz.dove.mpcli.api.service.conf.MenuWsUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.menu.req.MenuReq;
import com.sinovatech.rd.wcsb.cli.api.menu.rsp.MenuRsp;
import com.sinovatech.rd.wcsb.cli.api.menu.service.impl.MenuServiceImpl;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.mpcli.api.menu.service.MenuMpService;
import com.sinovatech.rd.wcsb.mpcli.api.security.service.AcTokenMpService;

/**
 * 
 * TODO: 菜单服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class MenuMpServiceImpl extends MenuServiceImpl implements MenuMpService {

    @Autowired
    private AcTokenMpService acTokenMpService;

    /**
     * 创建菜单
     * 
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param req 菜单请求VO 
     * @return
     */
    @Override
    public CommRsp createMenu(long sn, String cliAppCode, MenuReq req) {
        //
        return this.createMenu(MenuWsUrl.WSC04001MP, sn, cliAppCode, req);
    }

    /**
     * 删除菜单
     * 
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param devAcCode
     * @return
     */
    @Override
    public CommRsp delMenu(long sn, String cliAppCode, String devAcCode) {
        //
        return this.delMenu(MenuWsUrl.WSC04003MP, sn, cliAppCode, devAcCode);
    }

    /**
     * 查询菜单
     * 
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param devAcCode
     * @return
     */
    @Override
    public MenuRsp getMenu(long sn, String cliAppCode, String devAcCode) {
        //
        return this.getMenu(MenuWsUrl.WSC04002MP, sn, cliAppCode, devAcCode);
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenMpService.getAccessTokenMp(sn, cliAppCode, devAcCode));
    }

}
