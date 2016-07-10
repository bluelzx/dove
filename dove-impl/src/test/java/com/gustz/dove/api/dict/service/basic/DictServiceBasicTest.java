/*
 * @(#)SysDictServiceBasicTest.java
 *
 * @Copyright(c) 2015 Beijing Sinova Technologies team. All rights reserved.
 *
 */
package com.gustz.dove.api.dict.service.basic;

import java.util.Date;

import com.gustz.dove.api.service.simple.DataServiceTestBase;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.api.dict.service.DictService;
import com.sinovatech.rd.wcsb.api.dict.vo.DictVo;

/**
 * 
 * TODO: 字典接口实现的BASIC测试
 *
 * @author ZHENFENG ZHANG
 * @since  [Jan 27, 2015]
 */
public class DictServiceBasicTest extends DataServiceTestBase<DictVo, String> {

    private Object temp;

    @Autowired
    public void needService(DictService service) {
        super.setService(service);
    }

    @Override
    protected void changeVo(DictVo vo) throws Exception {
        vo.setDataKey("newkey");
        temp = vo.getDataKey();
    }

    @Override
    protected void compareVo(DictVo loaded, DictVo memoried) throws Exception {
        System.out.println("temp=:" + temp + ",loaded.getDataKey()=:" + loaded.getDataKey());
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
    protected DictVo prepareVo() throws Exception {
        DictVo vo = new DictVo();
        vo.setCreateTime(new Date());
        vo.setDataKey("key-tmp");
        vo.setDataValue("value-tmp");
        vo.setRemarks("备注-tmp");
        temp = vo.getDataKey();
        return vo;
    }

    @Override
    protected void setId(DictVo vo, String id) {
        vo.setId(id);
    }

}
