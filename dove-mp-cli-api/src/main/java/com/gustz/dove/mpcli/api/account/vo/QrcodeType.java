package com.gustz.dove.mpcli.api.account.vo;

import com.sinovatech.rd.wcsb.cli.api.service.BaseCliDict;

/**
 * TODO: 二维码类型
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public enum QrcodeType implements BaseCliDict {

    /**
     * QR_SCENE临时二维码
     */
    QR_SCENE("QR_SCENE", "临时二维码"),

    /**
     * QR_LIMIT_SCENE永久二维码
     */
    QR_LIMIT_SCENE("QR_LIMIT_SCENE", "永久二维码"),

    /**
     * QR_LIMIT_STR_SCENE永久的字符串参数值
     */
    QR_LIMIT_STR_SCENE("QR_LIMIT_STR_SCENE", "永久的字符串参数值");

    private final String name;

    private final String value;

    private QrcodeType(String name, String value) {
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

}
