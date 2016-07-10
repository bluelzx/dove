package com.gustz.dove.cli.api.menu.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.menu.rsp.MenuRsp.MenuBodyRsp;
import com.gustz.dove.cli.api.menu.vo.Menu;
import com.gustz.dove.cli.api.service.vo.AbstBaseRsp;
import com.gustz.dove.cli.api.service.vo.ErrorBodyRsp;

/**
 * 
 * TODO: 菜单响应消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class MenuRsp extends AbstBaseRsp<MenuBodyRsp> {

    private static final long serialVersionUID = 1L;

    public MenuRsp(long sn, String websCode) {
        super(sn, websCode, null, new MenuBodyRsp());
    }

    public static class MenuBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 菜单
         */
        @JsonProperty("menu")
        private Menu menu = new Menu();

        /**
         * 菜单列表
         */
        @JsonProperty("menulist")
        private Menu[] menulist = new Menu[] {};

        public MenuBodyRsp() {
            super();
        }

        public MenuBodyRsp(Menu menu) {
            this.menu = menu;
        }

        public Menu getMenu() {
            return menu;
        }

        public void setMenu(Menu menu) {
            this.menu = menu;
        }
    }
}
