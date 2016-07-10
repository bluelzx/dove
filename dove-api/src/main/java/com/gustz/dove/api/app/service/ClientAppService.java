/*
 * @(#)ClientAppService.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.gustz.dove.api.app.service;

import java.util.List;
import java.util.Map;

import com.sinovatech.fw.api.service.DataService;
import com.sinovatech.fw.api.vo.Order;
import com.gustz.dove.api.app.vo.ClientAppVo;

/**
 * TODO: 客户端应用服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public interface ClientAppService extends DataService<ClientAppVo, String> {

    /**
     * 分页查询列表 EC
     * 
     * @param search
     * @param start
     * @param limit
     * @param orders
     * @return
     */
    List<ClientAppVo> ecList(ClientAppVo search, int start, int limit, List<Order> orders);

    /**
     * 查询总记录数 EC
     * 
     * @param search
     * @return
     */
    int ecCount(ClientAppVo search);

    /**
     * 查询全部
     * 
     * @param search
     * @return
     */
    List<ClientAppVo> listAll(ClientAppVo search);

    /**
     * 改变状态
     * 
     * @param id
     * @param status
     */
    void chgStatus(String id, String status);

    /**
     * 改变为开发接入状态
     * 
     * @param id
     */
    void chgDevStatus(String id);

    /**
     * 获取有效的客户端应用名称
     * 
     * @return key=CliAppCode value=cliAppName
     */
    Map<String, String> getActiCliAppName();

}
