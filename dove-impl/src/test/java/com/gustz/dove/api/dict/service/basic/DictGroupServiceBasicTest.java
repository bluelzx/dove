/*
 * @(#)SysDictGroupServiceBasicTest.java
 *
 * @Copyright(c) 2015 Beijing Sinova Technologies team. All rights reserved.
 *
 */
package com.gustz.dove.api.dict.service.basic;

import java.util.Date;

import com.gustz.dove.api.service.simple.DataServiceTestBase;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.api.dict.service.DictGroupService;
import com.sinovatech.rd.wcsb.api.dict.vo.DictGroupVo;

/**
 * 
 * TODO: 字典组别接口实现的BASIC测试
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
public class DictGroupServiceBasicTest extends DataServiceTestBase<DictGroupVo, String> {

    private Object temp;

    @Autowired
    public void needService(DictGroupService service) {
        super.setService(service);
    }

    @Override
    protected void changeVo(DictGroupVo vo) throws Exception {
        vo.setGroupName("字典组别名称-change");
        temp = vo.getGroupName();
    }

    @Override
    protected void compareVo(DictGroupVo loaded, DictGroupVo memoried) throws Exception {
        System.out.println("temp=:" + temp + ",loaded.getGroupName()=:" + loaded.getGroupName());
        System.out.println("");
        try {
            //新的
            loaded.setCreateTime(this.fmtDateTime(loaded.getCreateTime()));
            //旧的
            memoried.setCreateTime(this.fmtDateTime(memoried.getCreateTime()));
        } catch (Exception e) {
            throw e;
        }
        super.compareVo(loaded, memoried);
    }

    @Override
    protected DictGroupVo prepareVo() throws Exception {
        DictGroupVo vo = new DictGroupVo();
        vo.setCreateTime(new Date());
        vo.setGroupCode("code-tmp");
        vo.setGroupName("字典组别名称1");
        vo.setRemarks("备注-tmp");
        temp = vo.getGroupName();
        return vo;
    }

    @Override
    protected void setId(DictGroupVo vo, String id) {
        vo.setId(id);
    }

}
