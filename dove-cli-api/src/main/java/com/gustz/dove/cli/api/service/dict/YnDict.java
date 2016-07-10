package com.gustz.dove.cli.api.service.dict;

import javax.xml.bind.annotation.XmlEnumValue;

import com.gustz.dove.cli.api.service.BaseCliDict;

/**
 * TODO: 是否类型字典
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum YnDict implements BaseCliDict {

    /**
     * 是1
     */
    @XmlEnumValue("1")
    Y("1", "是"),

    /**
     * 否0
     */
    @XmlEnumValue("0")
    N("0", "否");

    private final String name;

    private final String value;

    private YnDict(String name, String value) {
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
