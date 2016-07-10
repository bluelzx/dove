/*
 * @(#)CommConstants.java
 *
 * @Copyright(c) 2014 Beijing Sinova Technologies team. All rights reserved.
 *
 */
package com.gustz.dove.api.service.util;

import java.nio.charset.Charset;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * TODO: 公用常量
 * 
 * @author ZHENFENG ZHANG
 * @since [Dec 9, 2014]
 */
public abstract class CommConstants {

    /**
     * 是
     */
    public static final String YES = "Y";

    /**
     * 否
     */
    public static final String NO = "N";

    /**
     * R0
     */
    public static final String ZERO = "R0";

    /**
     * R1
     */
    public static final String ONE = "R1";

    /**
     * 启用1
     */
    public static final String ENABLE = ONE;

    /**
     * 停用0
     */
    public static final String DISABLE = ZERO;

    /**
     * 开始页数 1
     */
    public static final int START_PAGE = 1;

    /**
     * 每页数 Integer.MAX_VALUE
     */
    public static final int LIMIT_MAX = Integer.MAX_VALUE;

    public static final String KEY = "key";

    public static final String VAL = "value";

    public static final String TXT = "text";

    /**
     * Charset UTF-8 
     */
    public static final Charset CHARSET = Charset.forName("utf-8");

    /**
     * 获取公用的编码
     * <pre>
     * yyyyMMddHHmmssSSS
     * </pre>
     * @return
     */
    public static final String getCommCode() {
        return FastDateFormat.getInstance("yyyyMMddHHmmssSSS").format(new Date());
    }

    /**
     * 获取分页的开始记录序号
     * 
     * @param currPage
     * @param limit
     * @return
     */
    public static final int getStartPaging(int currPage, int limit) {
        if (currPage < 1) {
            currPage = 1;
        }
        if (limit < 1) {
            limit = 1;
        }
        return (currPage - 1) * limit + 1;
    }

    /**
     * 获取分页的总页数
     * 
     * @param count
     * @param limit 
     * @return
     */
    public static final int getTotalPage(int count, int limit) {
        if (count < 1) {
            count = 1;
        }
        if (limit < 1) {
            limit = 1;
        }
        if ((count % limit == 0)) {
            return (count / limit);
        } else {
            return (count / limit) + 1;
        }
    }

}
