package com.gustz.dove.cli.api.customer.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 文本消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class TextCust extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 回复的消息内容
     */
    @JsonProperty("content")
    private String content;

    public TextCust() {
        super();
    }

    public TextCust(String content) {
        this();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
