package com.gustz.dove.cli.api.customer.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 多媒体类
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class MediaCust extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 媒体ID
     */
    @JsonProperty("media_id")
    private String mediaId;

    public MediaCust() {
        super();
    }

    public MediaCust(String mediaId) {
        this();
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
