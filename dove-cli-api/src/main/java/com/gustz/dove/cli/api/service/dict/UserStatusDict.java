package com.gustz.dove.cli.api.service.dict;

import com.gustz.dove.cli.api.service.BaseCliDict;

/**
 * TODO: 用户状态字典
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum UserStatusDict implements BaseCliDict {

    /**
     * 0获取全部成员
     */
    ALL(0, "获取全部成员"),

    /**
     * 1获取已关注成员列表
     */
    SUBSCRIBE(1, "获取已关注成员列表"),

    /**
     * 2获取禁用成员列表
     */
    DISABLE(2, "获取禁用成员列表"),

    /**
     * 3备用
     */
    BAK(3, "备用"),

    /**
     * 4获取未关注成员列表
     */
    UN_SUBSCRIBE(4, "获取未关注成员列表");

    private final int name;

    private final String value;

    private UserStatusDict(int name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name + "";
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public boolean equals(UserStatusDict status) {
        return this.compareTo(status) == 0;
    }

}
