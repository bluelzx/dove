package com.gustz.dove.mpcli.api.user.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 关注的用户信息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class MpUser extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    @JsonProperty("subscribe")
    private int subscribe;

    /**
     * 用户的标识，对当前公众号唯一
     */
    @JsonProperty("openid")
    private String openId;

    /**
     * 用户的昵称
     */
    @JsonProperty("nickname")
    private String nickName;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    @JsonProperty("sex")
    private int gender;

    /**
     * 用户所在城市
     */
    @JsonProperty("city")
    private String city;

    /**
     * 用户所在国家
     */
    @JsonProperty("country")
    private String country;

    /**
     * 用户所在省份
     */
    @JsonProperty("province")
    private String province;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    @JsonProperty("language")
    private String language;

    /**
     * 用户头像，最后一个数值代表正方形头像大小
     * （有0、46、64、96、132数值可选，0代表640*640正方形头像），
     * 用户没有头像时该项为空
     */
    @JsonProperty("headimgurl")
    private String headImgUrl;

    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @JsonProperty("subscribe_time")
    private Date subscribeTime;

    /**
     * 微信特权
     * 
     * @return
     */
    @JsonProperty("privilege")
    private String privilege;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    @JsonProperty("unionid")
    private String unionId;

    /**
     * 公众号运营者对粉丝的备注
     */
    @JsonProperty("remark")
    private String remark;

    /**
     * 用户所在的分组ID 
     */
    @JsonProperty("groupid")
    private String groupId;

    public MpUser() {
        super();
    }

    public MpUser(int subscribe, String openId, String nickName, int gender, String city, String country, String province,
            String language, String headImgUrl, Date subscribeTime, String privilege) {
        this();
        this.subscribe = subscribe;
        this.openId = openId;
        this.nickName = nickName;
        this.gender = gender;
        this.city = city;
        this.country = country;
        this.province = province;
        this.language = language;
        this.headImgUrl = headImgUrl;
        this.subscribeTime = subscribeTime;
        this.privilege = privilege;
    }

    public MpUser(int subscribe, String openId, String nickName, int gender, String city, String country, String province,
            String language, String headImgUrl, Date subscribeTime, String privilege, String unionId, String remark,
            String groupId) {
        this(subscribe, openId, nickName, gender, city, country, province, language, headImgUrl, subscribeTime, privilege);
        this.unionId = unionId;
        this.remark = remark;
        this.groupId = groupId;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

}
