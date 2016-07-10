package com.gustz.dove.cli.api.customer.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 音乐
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class MusicCust extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 音乐名称
     */
    @JsonProperty("title")
    private String title;

    /**
     * 音乐描述
     */
    @JsonProperty("description")
    private String desc;

    /**
     * 音乐链接
     */
    @JsonProperty("musicurl")
    private String musicUrl;

    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    @JsonProperty("hqmusicurl")
    private String hQMusicUrl;

    /**
     * 缩略图的媒体id，通过上传多媒体文件，得到的id
     */
    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

    public MusicCust() {
        super();
    }

    public MusicCust(String title, String desc, String musicUrl, String hQMusicUrl, String thumbMediaId) {
        this();
        this.title = title;
        this.desc = desc;
        this.musicUrl = musicUrl;
        this.hQMusicUrl = hQMusicUrl;
        this.thumbMediaId = thumbMediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
