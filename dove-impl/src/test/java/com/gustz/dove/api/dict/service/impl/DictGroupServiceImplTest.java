/*
 * @(#)SysDictGroupServiceImplTest.java
 *
 * @Copyright(c) 2015 Beijing Sinova Technologies team. All rights reserved.
 *
 */
package com.gustz.dove.api.dict.service.impl;

import java.util.List;

import com.gustz.dove.api.service.base.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.api.dict.service.DictGroupService;
import com.sinovatech.rd.wcsb.api.dict.vo.DictGroupVo;
import com.sinovatech.rd.wcsb.repo.dict.po.DictGroupPo;

/**
 * TODO: 字典组别服务接口的实现测试
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
public class DictGroupServiceImplTest extends TestBase<DictGroupVo, DictGroupPo> {

    private DictGroupVo vo = null;

    @Autowired
    private DictGroupService dictGroupService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        vo = this.uniqueTestVo();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testIsExistGc() {
        boolean flag = this.dictGroupService.isExistGc(vo.getId(), vo.getGroupCode());
        System.out.println("sysDictGroupService.isExist-flag=:" + flag);
        Assert.assertTrue(flag);
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.api.account.service.impl.DictGroupServiceImpl.api.SysDictGroupServiceImpl.service.impl.DictGroupServiceImpl#ecList(com.sinovatech.rd.wcsb.api.dict.vo.DictGroupVo.api.SysDictGroupVo.vo.DictGroupVo, int, int, java.util.List)}.
     */
    @Test
    public void testEcList() {
        int start = 1;
        int limit = 10;
        DictGroupVo search = new DictGroupVo();
        search.setGroupCode(vo.getGroupCode());
        search.setGroupName(vo.getGroupName());
        // do search
        List<DictGroupVo> _list = this.dictGroupService.ecList(search, start, limit, null);
        System.out.println("sysDictGroupService.ecList-list=:" + _list);
        Assert.assertNotEquals(0, _list.size());
        this.compareVo(_list.get(0), vo);
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.api.account.service.impl.DictGroupServiceImpl.api.SysDictGroupServiceImpl.service.impl.DictGroupServiceImpl#ecCount(com.sinovatech.rd.wcsb.api.dict.vo.DictGroupVo.api.SysDictGroupVo.vo.DictGroupVo)}.
     */
    @Test
    public void testEcCount() {
        DictGroupVo search = new DictGroupVo();
        search.setGroupCode(vo.getGroupCode());
        search.setGroupName(vo.getGroupName());
        // do search
        int _size = this.dictGroupService.ecCount(search);
        System.out.println("sysDictGroupService.ecCount-size=:" + _size);
        Assert.assertNotEquals(0, _size);
    }

}
