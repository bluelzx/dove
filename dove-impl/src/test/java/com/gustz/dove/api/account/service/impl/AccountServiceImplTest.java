/*
 * @(#)AccountServiceImplTest.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.gustz.dove.api.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.api.account.service.AccountService;
import com.sinovatech.rd.wcsb.api.account.vo.AccountVo;
import com.gustz.dove.api.service.base.TestBase;
import com.sinovatech.rd.wcsb.repo.account.po.AccountPo;

/**
 * TODO: 账户服务接口的实现测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 30, 2015 ]
 */
public class AccountServiceImplTest extends TestBase<AccountVo, AccountPo> {

    @Autowired
    private AccountService accountService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

}
