package com.gustz.dove.cli.api.service.dict;

import com.gustz.dove.cli.api.service.BaseCliDict;

/**
 * TODO: 性别类型字典
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum GenderDict implements BaseCliDict {

    /**
     * 1男性
     */
    MAN("1", "男性"),

    /**
     * 2女性
     */
    WOMAN("2", "女性");

    private final String name;

    private final String value;

    private GenderDict(String name, String value) {
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
