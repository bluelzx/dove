package com.gustz.dove.cpcli.api.security.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.cpcli.api.security.rsp.UserOAuthRsp.UserOAuthBodyRsp;

/**
 * 
 * TODO: OAuth授权用户的响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class UserOAuthRsp extends AbstBaseRsp<UserOAuthBodyRsp> {

    private static final long serialVersionUID = 1L;

    public UserOAuthRsp(long sn, String websCode) {
        super(sn, websCode, null, new UserOAuthBodyRsp());
    }

    public static class UserOAuthBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 成员UserID
         */
        @JsonProperty("UserId")
        private String userId;

        /**
         * 手机设备号(由微信在安装时随机生成，删除重装会改变，升级不受影响) 
         */
        @JsonProperty("DeviceId")
        private String deviceId;

        /**
         * 非企业成员的标识，对当前企业号唯一
         */
        @JsonProperty("OpenId")
        private String openId;

        public UserOAuthBodyRsp() {
            super();
        }

        public UserOAuthBodyRsp(String userId, String deviceId, String openId) {
            super();
            this.userId = userId;
            this.deviceId = deviceId;
            this.openId = openId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

    }
}
