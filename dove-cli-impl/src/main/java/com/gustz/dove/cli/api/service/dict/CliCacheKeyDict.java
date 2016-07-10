package com.gustz.dove.cli.api.service.dict;

import com.sinovatech.rd.wcsb.cli.api.service.BaseCliDict;

/**
 * TODO: 客户端缓存KEY字典
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum CliCacheKeyDict implements BaseCliDict {

    /**
     * 接口凭证KEY
     */
    AC_TOKEN_CKEY("AC_TOKEN_", "接口凭证KEY");

    private final String name;

    private final String value;

    private CliCacheKeyDict(String name, String value) {
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
