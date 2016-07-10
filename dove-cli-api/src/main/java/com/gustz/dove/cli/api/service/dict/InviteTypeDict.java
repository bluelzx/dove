package com.gustz.dove.cli.api.service.dict;

import com.gustz.dove.cli.api.service.BaseCliDict;

/**
 * TODO: 邀请类型类型字典
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum InviteTypeDict implements BaseCliDict {

    /**
     * 1 微信邀请
     */
    T1("1", "微信邀请"),

    /**
     * 2 邮件邀请
     */
    T2("2", "邮件邀请");

    private final String name;

    private final String value;

    private InviteTypeDict(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
