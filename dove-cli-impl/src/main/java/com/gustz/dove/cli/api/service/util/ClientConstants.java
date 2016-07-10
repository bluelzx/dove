package com.gustz.dove.cli.api.service.util;

import java.nio.charset.Charset;

/**
 * TODO: 公用常量
 * 
 * @author ZHENFENG ZHANG
 * @since [Dec 9, 2014]
 */
public abstract class ClientConstants {

    /**
     * 是
     */
    public static final String YES = "Y";

    /**
     * 否
     */
    public static final String NO = "N";

    public static final String SUCCESS_RSP = "success";

    public static final String FAIL_RSP = "fail";

    /**
     * Charset UTF-8 
     */
    public static final Charset CHARSET = Charset.forName("utf-8");

    /**
     * MD5
     */
    public static final String MD5_ALGO = "MD5";

    /**
     * SHA
     */
    public static final String SHA_ALGO = "SHA-1";

    /**
     * Base char 
     */
    public static final String BASE_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

}
