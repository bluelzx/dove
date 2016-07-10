package com.gustz.dove.cli.api.customer.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * TODO: 文章消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class ArticleCust extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 文章消息名称
     */
    @JsonProperty("title")
    private String title;

    /**
     * 文章消息描述
     */
    @JsonProperty("description")
    private String desc;

    /**
     * 图片链接，支持JPG、PNG格式，<br>
     * 较好的效果为大图640*320，小图80*80
     */
    @JsonProperty("picurl")
    private String picUrl;

    /**
     * 点击文章消息跳转链接
     */
    @JsonProperty("url")
    private String url;

    public ArticleCust() {
        super();
    }

    public ArticleCust(String title, String desc, String picUrl, String url) {
        this();
        this.title = title;
        this.desc = desc;
        this.picUrl = picUrl;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
