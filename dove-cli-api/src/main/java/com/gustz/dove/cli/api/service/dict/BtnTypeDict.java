package com.gustz.dove.cli.api.service.dict;

import javax.xml.bind.annotation.XmlEnumValue;

import com.gustz.dove.cli.api.service.BaseCliDict;

/**s
 * TODO: 按钮类型字典
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum BtnTypeDict implements BaseCliDict {

    /**
     * click 点击推事件
     */
    @XmlEnumValue("click")
    CLICK("click", "点击推事件"),

    /**
     * view 跳转URL
     */
    @XmlEnumValue("view")
    VIEW("view", "跳转URL"),

    /**
     * scancode_push 扫码推事件
     */
    @XmlEnumValue("scancode_push")
    SCANCODE_PUSH("scancode_push", "扫码推事件"),

    /**
     * scancode_waitmsg 扫码推事件且弹出“消息接收中”提示框
     */
    @XmlEnumValue("scancode_waitmsg")
    SCANCODE_WAITMSG("scancode_waitmsg", "扫码推事件且弹出“消息接收中”提示框"),

    /**
     * pic_sysphoto 弹出系统拍照发图
     */
    @XmlEnumValue("pic_sysphoto")
    PIC_SYSPHOTO("pic_sysphoto", "弹出系统拍照发图"),

    /**
     * pic_photo_or_album 弹出拍照或者相册发图
     */
    @XmlEnumValue("pic_photo_or_album")
    PIC_PHOTO_OR_ALBUM("pic_photo_or_album", "弹出拍照或者相册发图"),

    /**
     * pic_weixin 弹出微信相册发图器
     */
    @XmlEnumValue("pic_weixin")
    PIC_WEIXIN("pic_weixin", "弹出微信相册发图器"),

    /**
     * location_select 弹出地理位置选择器
     */
    @XmlEnumValue("location_select")
    LOCATION_SELECT("location_select", "弹出地理位置选择器"),

    /**
     * media_id 下发消息（除文本消息）
     */
    @XmlEnumValue("media_id")
    MEDIA_ID("media_id", "下发消息（除文本消息）"),

    /**
     * view_limited 跳转图文消息URL
     */
    @XmlEnumValue("view_limited")
    VIEW_LIMITED("view_limited", "跳转图文消息URL");

    private final String name;

    private final String value;

    private BtnTypeDict(String name, String value) {
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
