/*
 * @(#)SysDictServiceImplTest.java
 *
 * @Copyright(c) 2015 Beijing Sinova Technologies team. All rights reserved.
 *
 */
package com.gustz.dove.api.dict.service.impl;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.api.dict.service.DictService;
import com.sinovatech.rd.wcsb.api.dict.vo.DictGroupVo;
import com.sinovatech.rd.wcsb.api.dict.vo.DictVo;
import com.gustz.dove.api.service.base.TestBase;
import com.sinovatech.rd.wcsb.repo.dict.po.DictGroupPo;
import com.sinovatech.rd.wcsb.repo.dict.po.DictPo;

/**
 * TODO: 字典服务接口的实现测试
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 19, 2015]
 */
public class DictServiceImplTest extends TestBase<DictVo, DictPo> {

    @Autowired
    private DictService dictService;

    private DictVo vo = null;

    private DictGroupVo dictGroupVo = null;

    private DictVo tmpVo = null;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        //字典VO
        vo = this.uniqueTestVo();
        // 组别VO
        dictGroupVo = (DictGroupVo) this.uniqueTestVo(DictGroupVo.class, DictGroupPo.class, vo.getGroupCode());
        vo.setGroupCode(dictGroupVo.getGroupCode());
        // 字典VO2
        tmpVo = new DictVo();
        this.copyProperties(tmpVo, vo);
        tmpVo.setDataKey("key99");
        tmpVo.setDataValue("val99");
        tmpVo.setRemarks("备注99");
        tmpVo.setId(this.dictService.save(tmpVo));

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        this.dictService.delete(tmpVo.getId());
    }

    /**
     * Test method for {@link com.sinovatech.rd.DictServiceImpl.api.SysDictServiceImpl.sysDictService.impl.DictServiceImpl#listGroup(java.lang.String)}.
     */
    @Test
    public void testListGroup() {
        List<DictService.InnerDict> _list = this.dictService.listGroup(vo.getGroupCode());
        System.out.println("sysDictService.listGroup-list=:" + toString(_list));
        Assert.assertNotEquals(0, _list.size());
    }

    /**
     * Test method for {@link com.sinovatech.rd.DictServiceImpl.api.SysDictServiceImpl.sysDictService.impl.DictServiceImpl#mapGroup(java.lang.String)}.
     */
    @Test
    public void testMapGroup() {
        Map<String, String> _map = this.dictService.mapGroup(vo.getGroupCode());
        System.out.println("sysDictService.mapGroup-map=:" + _map);
        Assert.assertNotEquals(0, _map.size());
    }

    /**
     * Test method for {@link com.sinovatech.rd.DictServiceImpl.api.SysDictServiceImpl.sysDictService.impl.DictServiceImpl#refresh()}.
     */
    @Test
    public void testRefresh() {
        this.dictService.refreshDict();
        Map<String, String> _map = this.dictService.mapGroup(vo.getGroupCode());
        boolean flag = _map.containsValue(tmpVo.getDataValue());
        System.out.println("sysDictService.refresh-map=:" + _map + ",value=:" + tmpVo.getDataValue());
        System.out.println("sysDictService.refresh-flag=:" + flag);
        Assert.assertTrue(flag);
    }

    /**
     * Test method for {@link com.sinovatech.rd.DictServiceImpl.api.SysDictServiceImpl.sysDictService.impl.DictServiceImpl#text(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testText() {
        String _rs = dictService.text(vo.getGroupCode(), vo.getDataKey());
        System.out.println("sysDictService.text-rs=:" + _rs);
        Assert.assertEquals(_rs, vo.getDataValue());
    }

    /** 
     * Test method for {@link com.sinovatech.rd.DictServiceImpl.api.SysDictServiceImpl.sysDictService.impl.DictServiceImpl#texts(java.lang.String, java.lang.String[])}.
     */
    @Test
    public void testTexts() {
        this.dictService.refreshDict();
        String[] _keys = { vo.getDataKey(), tmpVo.getDataKey() };
        String _rs = this.dictService.texts(vo.getGroupCode(), _keys);
        System.out.println("sysDictService.texts-rs=:" + _rs);
        String _vals = vo.getDataValue() + "," + tmpVo.getDataValue();
        Assert.assertEquals(_rs, _vals);
    }

    /**
     * Test method for {@link com.sinovatech.rd.DictServiceImpl.api.SysDictServiceImpl.sysDictService.impl.DictServiceImpl#isExist(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testIsExistKey() {
        boolean flag = this.dictService.isExistKey(vo.getId(), vo.getGroupCode(), vo.getDataKey());
        System.out.println("sysDictService.isExist-flag=:" + flag);
        Assert.assertTrue(flag);
    }

    /**
     * Test method for {@link com.sinovatech.rd.DictServiceImpl.api.SysDictServiceImpl.sysDictService.impl.DictServiceImpl#ecList(com.sinovatech.rd.wcsb.api.dict.vo.DictVo.api.SysDictVo.vo.DictVo, int, int, java.util.List)}.
     */
    @Test
    public void testEcList() {
        int start = 1;
        int limit = 10;
        DictVo search = new DictVo();
        search.setDataKey(vo.getDataKey());
        search.setDataValue(vo.getDataValue());
        // do search
        List<DictVo> _list = this.dictService.ecList(search, start, limit, null);
        Assert.assertNotEquals(0, _list.size());
        this.compareVo(_list.get(0), vo);
    }

    /**
     * Test method for {@link com.sinovatech.rd.DictServiceImpl.api.SysDictServiceImpl.sysDictService.impl.DictServiceImpl#ecCount(com.sinovatech.rd.wcsb.api.dict.vo.DictVo.api.SysDictVo.vo.DictVo)}.
     */
    @Test
    public void testEcCount() {
        DictVo search = new DictVo();
        search.setDataKey(vo.getDataKey());
        search.setDataValue(vo.getDataValue());
        // do search
        int _size = this.dictService.ecCount(search);
        System.out.println("sysDictService.ecCount-size=:" + _size);
        Assert.assertNotEquals(0, _size);
    }

    @Test
    public void testIsExistFkDict() {
        boolean flag = this.dictService.isExistFkDict(vo.getGroupCode());
        System.out.println("sysDictService.isExistFkDict-flag=:" + flag);
        Assert.assertTrue(flag);
    }

    public static void main(String[] args) throws Exception {
        // System.out.println("=:" + DesUitls.encrypt("a12345"));
        // System.out.println("=:" + DesUitls.decrypt("b1782b2c1253faff"));
        // System.out.println("=:" + DesUitls.decrypt("yFn+CyG8GQ="));
        //System.out.println(DesDecryptUtil.decryptForSSSO("123456565643450987657689876543267676787651234567", "yFn+CyG8GQ="));
    }

}
