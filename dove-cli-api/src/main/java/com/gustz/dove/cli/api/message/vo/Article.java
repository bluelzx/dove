package com.gustz.dove.cli.api.message.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gustz.dove.cli.api.service.util.CDataJaxbAdapter;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * TODO: 文章消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "item")
@JsonPropertyOrder("item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Article extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 文章消息名称
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @XmlElement(name = "Title")
    @JsonProperty("Title")
    private String title;

    /**
     * 文章消息描述
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @XmlElement(name = "Description")
    @JsonProperty("Description")
    private String desc;

    /**
     * 图片链接，支持JPG、PNG格式，<br>
     * 较好的效果为大图640*320，小图80*80
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @XmlElement(name = "PicUrl")
    @JsonProperty("PicUrl")
    private String picUrl;

    /**
     * 点击文章消息跳转链接
     */
    @XmlJavaTypeAdapter(CDataJaxbAdapter.class)
    @XmlElement(name = "Url")
    @JsonProperty("Url")
    private String url;

    public Article() {
        super();
    }

    public Article(String title, String desc, String picUrl, String url) {
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
