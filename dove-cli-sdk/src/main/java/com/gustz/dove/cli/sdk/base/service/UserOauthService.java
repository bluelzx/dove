package com.gustz.dove.cli.sdk.base.service;

import com.sinovatech.rd.wcsb.cpcli.api.addrbook.vo.CpUser;

/**
 * TODO: User OAUTH service I
 *
 * @author ZHENFENG ZHANG
 * @since [ Oct 26, 2015 ]
 */
public interface UserOauthService {

    /**
     * 接收消息的URI: /wcsbus/cli/receiveMsg
     */
    public static final String RECEIVE_MSG_URI = "/wcsbus/cli/receiveMsg";

    /**
     * CP授权的URI: /wcsbus/cp/doCpOauth
     */
    public static final String CP_OAUTH_URI = "/wcsbus/cp/doCpOauth";

    /**
     * 获取微信登录的CP用户
     * 
     * @param userId
     * @param deviceId
     * @return
     */
    CpUser getLoginCpUser(String userId, String deviceId);

    /**
     * 获取授权失败后的跳转URI
     * 
     * @return
     */
    String getOauthFailUri();

}
