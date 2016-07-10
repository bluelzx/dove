package com.gustz.dove.cli.sdk.base.util;

import com.sinovatech.rd.wcsb.cli.api.service.dict.UserStatusDict;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.vo.CpUser;

/**
 * TODO: CP user helper
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 24, 2015 ]
 */
public abstract class CpUserHelper {

    /**
     * Current CP user attribute key
     */
    public static final String CURR_CP_USER_AKEY = "curr_cp_user";

    private static final ThreadLocal<CpUser> CURR_USER_TL = new ThreadLocal<CpUser>();

    public static void setDev(String userId) {
        String flag = "";
        CpUser cpUser = new CpUser();
        cpUser.setUserId("adm123");
        if (userId == null || userId.isEmpty()) {
            cpUser.setUserId(userId);
            flag = "_" + userId;
        }
        cpUser.setWeixinId("wx_id123");
        cpUser.setStatus(UserStatusDict.SUBSCRIBE);
        cpUser.setName("超级管理员" + flag);
        cpUser.setDeviceCode("device_code" + flag);
        //
        CURR_USER_TL.set(cpUser);
    }

    public static void set(CpUser vo) {
        if (vo == null) {
            throw new IllegalArgumentException("Arg 'CpUser' is null.");
        }
        final String userId = vo.getUserId();
        final String deviceCode = vo.getDeviceCode();
        //
        if (isBlank(userId) || isBlank(deviceCode)) {
            throw new IllegalArgumentException("当前用户的编码/设备编码异常！");
        }
        CURR_USER_TL.set(vo);
    }

    public static CpUser getCurrCpUser() {
        return CURR_USER_TL.get();
    }

    public static String getUserId() {
        return getCurrCpUser().getUserId();
    }

    public static UserStatusDict getStatus() {
        return getCurrCpUser().getStatus();
    }

    public static String getDeviceCode() {
        return getCurrCpUser().getDeviceCode();
    }

    public static String getRealName() {
        return getCurrCpUser().getName();
    }

    public static String getLoginName() {
        return getCurrCpUser().getLoginName();
    }

    public static String getPosition() {
        return getCurrCpUser().getPosition();
    }

    public static String getMobile() {
        return getCurrCpUser().getMobile();
    }

    public static String getEmail() {
        return getCurrCpUser().getEmail();
    }

    public static String getWeixinId() {
        return getCurrCpUser().getWeixinId();
    }

    public static void clear() {
        CURR_USER_TL.remove();
    }

    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim();
        return (str.isEmpty() || "null".equalsIgnoreCase(str));
    }

}
