package com.gustz.dove.cli.api.service.dict;

import javax.xml.bind.annotation.XmlEnumValue;

import com.gustz.dove.cli.api.service.BaseCliDict;

/**
 * TODO: 消息类型字典
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum MsgTypeDict implements BaseCliDict {

    /**
     * 文本
     */
    @XmlEnumValue("text")
    TEXT("text", "文本"),

    /**
     * 图片
     */
    @XmlEnumValue("image")
    IMAGE("image", "图片"),

    /**
     * 视频
     */
    @XmlEnumValue("video")
    VIDEO("video", "视频"),

    /**
     * 小视频
     */
    @XmlEnumValue("shortvideo")
    SHORT_VIDEO("shortvideo", "小视频"),

    /**
     * 音乐
     */
    @XmlEnumValue("music")
    MUSIC("music", "音乐"),

    /**
     * 新闻
     */
    @XmlEnumValue("news")
    NEWS("news", "新闻"),

    /**
     * 图文新闻
     */
    @XmlEnumValue("mpnews")
    MPNEWS("mpnews", "图文新闻"),

    /**
     * 链接
     */
    @XmlEnumValue("link")
    LINK("link", "链接"),

    /**
     * 地理位置
     */
    @XmlEnumValue("location")
    LOCATION("location", "地理位置"),

    /**
     * 音频
     */
    @XmlEnumValue("voice")
    VOICE("voice", "音频"),

    /**
     * 事件
     */
    @XmlEnumValue("event")
    EVENT("event", "事件"),

    /**
     * 卡券
     */
    @XmlEnumValue("wxcard")
    WXCARD("wxcard", "卡券"),

    /**
     * 缩略图 主要用于视频与音乐格式的
     */
    @XmlEnumValue("thumb")
    THUMB("thumb", "缩略图"),

    /**
     * 文件消息
     */
    @XmlEnumValue("file")
    FILE("file", "文件");

    private final String name;

    private final String value;

    private MsgTypeDict(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
