package com.gustz.dove.cli.api.service.vo;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 
 * TODO: Client base VO
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 16, 2015 ]
 */
public abstract class AbstCliBaseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * VO to string
     */
    @Override
    public String toString() {
        return toString(this);
    }

    /**
     * To string
     * 
     * @param obj
     * @return
     */
    private static String toString(final Object obj) {
        Class<?> clazz = obj.getClass();
        StringBuilder sbd = new StringBuilder(clazz.getName());
        sbd.append("@").append(clazz.hashCode());
        try {
            sbd.append("[");
            int i = 0;
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (i > 0) {
                    sbd.append(", ");
                }
                field.setAccessible(true);
                sbd.append(field.getName());
                sbd.append("=");
                sbd.append(field.get(obj));
                i++;
            }
            sbd.append("]");
        } catch (Exception e) {
            //ignore exception
        }
        return sbd.toString();
    }

}
