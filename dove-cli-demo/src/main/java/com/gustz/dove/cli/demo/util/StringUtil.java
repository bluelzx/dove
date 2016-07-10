package com.sinovatech.rd.wcsb.cli.demo.util;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * TODO: String util
 *
 * @author ZHENFENG ZHANG
 * @since  [Apr 10, 2015]
 */
public abstract class StringUtil {

    /**
     * Object to string
     * 
     * @param obj
     * @return
     */
    public static final String toText(Object obj) {
        return ReflectionToStringBuilder.toString(obj);
    }

}
