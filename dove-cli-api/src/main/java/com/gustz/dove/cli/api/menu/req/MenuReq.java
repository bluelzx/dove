package com.gustz.dove.cli.api.menu.req;

import com.gustz.dove.cli.api.menu.vo.Menu;
import com.gustz.dove.cli.api.service.vo.AbstBaseReq;

/**
 * 
 * TODO: 菜单请求消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class MenuReq extends AbstBaseReq<Menu> {

    private static final long serialVersionUID = 1L;

    public MenuReq(String devAcCode, Menu body) {
        super(devAcCode, body);
    }

}
