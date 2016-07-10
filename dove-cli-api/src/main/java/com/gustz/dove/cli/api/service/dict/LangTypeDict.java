package com.gustz.dove.cli.api.service.dict;

import com.gustz.dove.cli.api.service.BaseCliDict;

/**
 * TODO: 常用语言类型字典
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum LangTypeDict implements BaseCliDict {

    /**
     * 中国zh_CN简体
     */
    CHINA("zh_CN", "中国简体"),

    /**
     * 中国台湾zh_TW繁体
     */
    CHINA_TW("zh_TW", "中国台湾繁体"),

    /**
     * 英国en英语
     */
    ENGLISH("en", "英语");

    private final String name;

    private final String value;

    private LangTypeDict(String name, String value) {
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
