package com.gustz.dove.cli.api.customer.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * TODO: 视频消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class VideoCust extends MediaCust {

    private static final long serialVersionUID = 1L;

    /**
     * 视频消息的标题
     */
    @JsonProperty("title")
    private String title;

    /**
     * 视频消息的描述
     */
    @JsonProperty("description")
    private String desc;

    /**
     * 缩略图媒体ID
     */
    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

    public VideoCust() {
        super();
    }

    public VideoCust(String title, String desc) {
        this();
        this.title = title;
        this.desc = desc;
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

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

}
