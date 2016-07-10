package com.sinovatech.rd.wcsb.cli.demo.user.vo;

import java.io.Serializable;

/**
 * 
 * TODO: 用户VO
 * 
 * @author ZHENFENG ZHANG
 * @since [2014-11-28]
 */
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    //private String id;

    // 用户编码
    private String userCode;

    // 用户名
    private String userName;

    // 用户角色类型
    private String roleType;
    private String roleTypeText;

    // 姓名
    private String realName;
    // 姓名拼音
    private String realPyName;
    // 所属部门id列表
    private String[] deptIds;

    // 所属部门名称列表
    private String[] deptNames;

    // 职位信息
    private String position;

    // 手机号码
    private String mobile;

    // 性别。0表示未定义，1表示男性，2表示女性
    private String gender;

    // 邮箱
    private String email;

    // 微信号
    private String weixinId;

    // 用户设备编码
    private String deviceCode;

    // 头像URL 注：如果要获取小图将url最后的"/0"改成"/64"即可
    private String avatarUrl;

    // 状态
    private String status;
    private String[] statusIn;

    public UserVo() {
        //null
    }

    /**
     * 
     * @param id
     * @param userCode
     * @param status
     */
    public UserVo(String userCode, String status) {
        this.userCode = userCode;
        this.status = status;
    }

    /**
     * 
     * @param id
     * @param userCode
     * @param status
     * @param realName
     * @param realPyName
     */
    public UserVo(String userCode, String status, String realName, String realPyName) {
        this(userCode, status);
        this.realName = realName;
        this.realPyName = realPyName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String[] getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String[] deptIds) {
        this.deptIds = deptIds;
    }

    public String[] getDeptNames() {
        return deptNames;
    }

    public String getRoleTypeText() {
        return roleTypeText;
    }

    public void setRoleTypeText(String roleTypeText) {
        this.roleTypeText = roleTypeText;
    }

    public void setDeptNames(String[] deptNames) {
        this.deptNames = deptNames;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getStatusIn() {
        return statusIn;
    }

    public void setStatusIn(String[] statusIn) {
        this.statusIn = statusIn;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getRealPyName() {
        return realPyName;
    }

    public void setRealPyName(String realPyName) {
        this.realPyName = realPyName;
    }

}
