package com.gustz.dove.web.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: JSON object mapper
 * 
 * @author ZHENFENG ZHANG
 * @since [Dec 2, 2014] 
 */
public class JSONObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    public JSONObjectMapper() {
        this("yyyy-MM-dd HH:mm:ss");
    }

    public JSONObjectMapper(String pattern) {
        // 允许单引号  
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 为NULL不序列化
        // this.setSerializationInclusion(Include.NON_NULL);
        // 字段和值都加引号  
        //this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 数字也加引号  
        //this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        // 设置日期格式
        super.setDateFormat(new SimpleDateFormat(pattern));
    }

    public String writeValueAsString(final boolean isFormat, Object bean) throws JsonProcessingException {
        if (isFormat) {
            return super.writerWithDefaultPrettyPrinter().writeValueAsString(bean);
        } else {
            return super.writeValueAsString(bean);
        }
    }

}
