/*
 * @(#)AddrListService.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.sinovatech.rd.wcsb.cli.demo.addr.service;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 通讯录服务
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 18, 2015 ]
 */
public class AddrListService {

    /**
     * 查询通讯录地址集
     * 
     * @return
     */
    public List<String> listAddr() {
        List<String> _list = new ArrayList<String>();
        _list.add("地址1");
        _list.add("地址2");
        _list.add("地址3");

        return _list;
    }

}
