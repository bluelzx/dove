package com.gustz.dove.cli.api.service.dict;

import javax.xml.bind.annotation.XmlEnumValue;

import com.gustz.dove.cli.api.service.BaseCliDict;

/**
 * TODO: 事件类型字典
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public enum EventTypeDict implements BaseCliDict {

    /**
     * subscribe 关注
     */
    @XmlEnumValue("subscribe")
    SUBSCRIBE("subscribe", "关注"),

    /**
     * unsubscribe 取消关注
     */
    @XmlEnumValue("unsubscribe")
    UN_SUBSCRIBE("unsubscribe", "取消关注"),

    /**
     * CLICK 自定义菜单点击
     */
    @XmlEnumValue("CLICK")
    CLICK("CLICK", "菜单点击"),

    /**
     * VIEW 自定义菜单跳转
     */
    @XmlEnumValue("VIEW")
    VIEW("VIEW", "菜单跳转"),

    /**
     * LOCATION 上报地理位置
     */
    @XmlEnumValue("LOCATION")
    LOCATION("LOCATION", "上报地理位置"),

    /**
     * SCAN二维码扫描（用户已关注时）
     */
    @XmlEnumValue("SCAN")
    SCAN("SCAN", "二维码扫描"),

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
     * enter_agent 成员进入应用
     */
    @XmlEnumValue("enter_agent")
    ENTER_AGENT("enter_agent", "成员进入应用"),

    /**
     * batch_job_result 异步任务完成
     */
    @XmlEnumValue("batch_job_result")
    BATCH_JOB_RS("batch_job_result", "异步任务完成");

    private final String name;

    private final String value;

    private EventTypeDict(String name, String value) {
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
