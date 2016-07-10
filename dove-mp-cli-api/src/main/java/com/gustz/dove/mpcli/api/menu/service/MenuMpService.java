package com.gustz.dove.mpcli.api.menu.service;

import com.sinovatech.rd.wcsb.cli.api.menu.req.MenuReq;
import com.sinovatech.rd.wcsb.cli.api.menu.rsp.MenuRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;

/**
 * 
 * TODO: 菜单服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public interface MenuMpService {

    /**
     * 创建菜单
     * 
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param req 菜单请求VO
     * @return
     */
    CommRsp createMenu(long sn, String cliAppCode, MenuReq req);

    /**
     * 删除菜单
     * 
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param devAcCode
     * @return
     */
    CommRsp delMenu(long sn, String cliAppCode, String devAcCode);

    /**
     * 查询菜单
     * 
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param devAcCode
     * @return
     */
    MenuRsp getMenu(long sn, String cliAppCode, String devAcCode);

}