package com.gustz.dove.api.menu.service;

import java.util.List;

import com.gustz.dove.api.dict.vo.ErrorVo;
import com.sinovatech.fw.api.service.DataService;
import com.sinovatech.fw.api.vo.Order;
import com.gustz.dove.api.menu.vo.AppMenuVo;

/**
 * TODO: 应用菜单服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public interface AppMenuService extends DataService<AppMenuVo, String> {

    /**
     * 分页查询列表 EC
     * 
     * @param search
     * @param start
     * @param limit
     * @param orders
     * @return
     */
    List<AppMenuVo> ecList(AppMenuVo search, int start, int limit, List<Order> orders);

    /**
     * 查询总记录数 EC
     * 
     * @param search
     * @return
     */
    int ecCount(AppMenuVo search);

    /**
     * 查询全部
     * 
     * @param search
     * @return
     */
    List<AppMenuVo> listAll(AppMenuVo search);

    /**
     * 是否存在菜单编码
     * 
     * @param id
     * @param menuCode
     * @return
     */
    boolean isExistMenuCode(String id, String menuCode);

    /**
     * 发布应用菜单
     *  
     * @param id
     * @param deployMenu
     * @throws Exception
     */
    ErrorVo deployMenu(String id, IDeployMenu deployMenu) throws Exception;

    public interface IDeployMenu {

        /**
         * Do deploy menu
         * 
         * @param appMenuVo
         * @return
         * @throws Exception
         */
        ErrorVo doDeployMenu(final AppMenuVo appMenuVo) throws Exception;
    }

}
