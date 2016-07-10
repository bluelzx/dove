package com.gustz.dove.cli.api.customer.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.dict.YnDict;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * TODO: 组合图文消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class MpArticleCust extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @JsonProperty("title")
    private String title;

    /**
     * 图文消息缩略图的media_id, 可以在上传多媒体文件接口中获得。此处thumb_media_id即上传接口返回的media_id
     */
    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

    /**
     * 作者
     */
    @JsonProperty("author")
    private String author;

    /**
     * 图文消息点击“阅读原文”之后的页面链接
     */
    @JsonProperty("content_source_url")
    private String contentSrcUrl;

    /**
     * 图文消息的内容，支持html标签
     */
    @JsonProperty("content")
    private String content;

    /**
     * 图文消息的描述
     */
    @JsonProperty("digest")
    private String desc;

    /**
     * 是否显示封面，1为显示，0为不显示
     */
    @JsonProperty("show_cover_pic")
    private YnDict showCoverPic;

    public MpArticleCust() {
        super();
    }

    public MpArticleCust(String title, String content) {
        this();
        this.title = title;
        this.content = content;
    }

    public MpArticleCust(String title, String thumbMediaId, String author, String contentSrcUrl, String content, String desc,
            YnDict showCoverPic) {
        this();
        this.title = title;
        this.thumbMediaId = thumbMediaId;
        this.author = author;
        this.contentSrcUrl = contentSrcUrl;
        this.content = content;
        this.desc = desc;
        this.showCoverPic = showCoverPic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContentSrcUrl() {
        return contentSrcUrl;
    }

    public void setContentSrcUrl(String contentSrcUrl) {
        this.contentSrcUrl = contentSrcUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public YnDict getShowCoverPic() {
        return showCoverPic;
    }

    public void setShowCoverPic(YnDict showCoverPic) {
        this.showCoverPic = showCoverPic;
    }

}
