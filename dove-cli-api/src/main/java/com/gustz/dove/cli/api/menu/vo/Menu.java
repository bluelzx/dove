/*
 * @(#)Menu.java
 *
 * @Copyright(c) 2015 All rights reserved.
 *
 */
package com.gustz.dove.cli.api.menu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * TODO: 菜单
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
public class Menu extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单按钮
     */
    @JsonProperty("button")
    private Button[] buttons = new Button[] {};

    public Menu() {
        super();
    }

    public Menu(Button[] buttons) {
        super();
        this.buttons = buttons;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }
}
