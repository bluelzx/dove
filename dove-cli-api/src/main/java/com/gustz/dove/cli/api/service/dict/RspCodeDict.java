package com.gustz.dove.cli.api.service.dict;

import com.gustz.dove.cli.api.service.BaseCliDict;

/**
 * TODO: 响应编码的字典
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum RspCodeDict implements BaseCliDict {

    /**
     * 操作成功WCSB0000
     */
    A0000("WCSB0000", "操作成功"),

    /**
     * 系统内部异常WCSB9999
     */
    J9999("WCSB9999", "系统内部异常"),

    /**
     * 连接第三方超时 -1
     */
    J9998X("-1", "连接第三方超时"),

    /**
     * 请求URL为空WCSB0001
     */
    A0001("WCSB0001", "请求URL为空"),

    /**
     * 客户端appCode为空WCSB0002
     */
    A0002("WCSB0002", "客户端appCode为空"),

    /**
     * 请求报文为空WCSB0003
     */
    A0003("WCSB0003", "请求报文为空"),

    /**
     * 响应报文为空WCSB0004
     */
    A0004("WCSB0004", "响应报文为空"),

    /**
     * 客户端appCode未授权WCSB0005
     */
    A0005("WCSB0005", "客户端appCode未授权"),

    /**
     * 下载文件失败
     */
    A0006("WCSB0006", "下载文件失败");

    private final String name;

    private final String value;

    private RspCodeDict(String name, String value) {
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
