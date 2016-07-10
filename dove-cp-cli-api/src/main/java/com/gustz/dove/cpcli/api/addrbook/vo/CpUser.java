package com.gustz.dove.cpcli.api.addrbook.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.dict.GenderDict;
import com.sinovatech.rd.wcsb.cli.api.service.dict.UserStatusDict;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 用户信息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class CpUser extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 成员UserID 对应管理端的帐号
     */
    @JsonProperty("userid")
    private String userId;

    /**
     * 成员名称
     */
    @JsonProperty("name")
    private String name;

    /**
     * 成员所属部门id列表
     */
    @JsonProperty("department")
    private int[] deptIds;

    /**
     * 职位信息
     */
    @JsonProperty("position")
    private String position;

    /**
     * 手机号码
     */
    @JsonProperty("mobile")
    private String mobile;

    /**
     * 性别。0表示未定义，1表示男性，2表示女性
     */
    @JsonProperty("gender")
    private GenderDict gender;

    /**
     * 邮箱
     */
    @JsonProperty("email")
    private String email;

    /**
     * 微信号
     */
    @JsonProperty("weixinid")
    private String weixinId;

    /**
     *  头像URL 注：如果要获取小图将url最后的"/0"改成"/64"即可
     */
    @JsonProperty("avatar")
    private String avatarUrl;

    /**
     * 关注状态: 1=已关注，2=已冻结，4=未关注
     */
    @JsonProperty("status")
    private UserStatusDict status;

    /**
     * 扩展属性
     */
    @JsonProperty("extattr")
    private ExtAttr extAttr;

    // ------------ 扩展属性 ------------ begin
    /**
     * 设备编码
     */
    @JsonIgnore
    private String deviceCode;

    /**
     * 登录名称
     */
    @JsonIgnore
    private String loginName;

    // ------------ 扩展属性 ------------ end

    public CpUser() {
        super();
    }

    /**
     * 
     * @param userId
     */
    public CpUser(String userId) {
        super();
        this.userId = userId;
    }

    /**
     * 
     * @param userId
     * @param realName
     * @param mobile
     * @param email
     * @param weixinId
     * @param deviceCode
     * @param loginName
     */
    public CpUser(String userId, String realName, String mobile, String email, String weixinId, String deviceCode,
            String loginName) {
        super();
        this.userId = userId;
        this.name = realName;
        this.mobile = mobile;
        this.email = email;
        this.weixinId = weixinId;
        this.deviceCode = deviceCode;
        this.loginName = loginName;
    }

    /**
     * 
     * @param userId
     * @param name
     * @param deptIds
     * @param position
     * @param mobile
     * @param gender
     * @param email
     * @param weixinId
     * @param avatarUrl
     * @param status
     * @param extAttr
     */
    public CpUser(String userId, String name, int[] deptIds, String position, String mobile, GenderDict gender, String email,
            String weixinId, String avatarUrl, UserStatusDict status, ExtAttr extAttr) {
        super();
        this.userId = userId;
        this.name = name;
        this.deptIds = deptIds;
        this.position = position;
        this.mobile = mobile;
        this.gender = gender;
        this.email = email;
        this.weixinId = weixinId;
        this.avatarUrl = avatarUrl;
        this.status = status;
        this.extAttr = extAttr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(int[] deptIds) {
        this.deptIds = deptIds;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public GenderDict getGender() {
        return gender;
    }

    public void setGender(GenderDict gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public UserStatusDict getStatus() {
        return status;
    }

    public void setStatus(UserStatusDict status) {
        this.status = status;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public ExtAttr getExtAttr() {
        return extAttr;
    }

    public void setExtAttr(ExtAttr extAttr) {
        this.extAttr = extAttr;
    }

    public static class ExtAttr extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 属性对象
         */
        @JsonProperty("attrs")
        private Attrs[] attrs;

        public ExtAttr() {
            super();
        }

        public ExtAttr(Attrs[] attrs) {
            super();
            this.attrs = attrs;
        }

        public Attrs[] getAttrs() {
            return attrs;
        }

        public void setAttrs(Attrs[] attrs) {
            this.attrs = attrs;
        }

        public static class Attrs extends AbstCliBaseVo {

            private static final long serialVersionUID = 1L;

            /**
             * 属性名称
             */
            @JsonProperty("name")
            private String name;

            /**
             * 属性值
             */
            @JsonProperty("value")
            private String value;

            public Attrs() {
                super();
            }

            public Attrs(String name, String value) {
                super();
                this.name = name;
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

}
