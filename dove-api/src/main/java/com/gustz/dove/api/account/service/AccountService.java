/*
 * @(#)AccountService.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.gustz.dove.api.account.service;

import java.util.List;
import java.util.Map;

import com.gustz.dove.api.account.vo.AccountVo;
import com.sinovatech.fw.api.service.DataService;
import com.sinovatech.fw.api.vo.Order;

/**
 * TODO: 账户服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public interface AccountService extends DataService<AccountVo, String> {

    /**
     * 分页查询列表 EC
     * 
     * @param search
     * @param start
     * @param limit
     * @param orders
     * @return
     */
    List<AccountVo> ecList(AccountVo search, int start, int limit, List<Order> orders);

    /**
     * 查询总记录数 EC
     * 
     * @param search
     * @return
     */
    int ecCount(AccountVo search);

    /**
     * 查询全部
     * 
     * @param search
     * @return
     */
    List<AccountVo> listAll(AccountVo search);

    /**
     * 查询全部可用的
     * 
     * @param accountType
     * @return
     */
    List<AccountVo> listActive(String accountType);

    /**
     * 是否存在唯一凭证
     * 
     * @param id
     * @param appId
     * @return
     */
    boolean isExistAppId(String id, String appId);

    /**
     * 是否存在凭证密钥
     * 
     * @param id
     * @param appSecret
     * @return
     */
    boolean isExistAppSecret(String id, String appSecret);

    /**
     * 改变状态
     * 
     * @param id
     * @param status
     */
    void chgStatus(String id, String status);

    /**
     * 获取有效的账户
     * 
     * @return
     */
    Map<String, String> getActiveAcc();

}
