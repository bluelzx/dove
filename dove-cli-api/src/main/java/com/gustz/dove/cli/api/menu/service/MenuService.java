package com.gustz.dove.cli.api.menu.service;

import com.gustz.dove.cli.api.menu.req.MenuReq;
import com.gustz.dove.cli.api.menu.rsp.MenuRsp;
import com.gustz.dove.cli.api.service.BaseWebsUrl;
import com.gustz.dove.cli.api.service.vo.CommRsp;

/**
 * 
 * TODO: 菜单服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public interface MenuService {

    /**
     * 创建菜单
     * 
     * @param websUrl
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param req 菜单请求VO
     * @return
     */
    CommRsp createMenu(BaseWebsUrl websUrl, long sn, String cliAppCode, MenuReq req);

    /**
     * 删除菜单
     * 
     * @param websUrl
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param devAcCode
     * @return
     */
    CommRsp delMenu(BaseWebsUrl websUrl, long sn, String cliAppCode, String devAcCode);

    /**
     * 查询菜单
     * 
     * @param websUrl
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param devAcCode
     * @return
     */
    MenuRsp getMenu(BaseWebsUrl websUrl, long sn, String cliAppCode, String devAcCode);

}