package com.gustz.dove.cpcli.api.security.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.cpcli.api.security.req.AcTokenOAuthReq.AcTokenOAuthBodyReq;

/**
 * 
 * TODO: OAuth授权凭证的请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class AcTokenOAuthReq extends AbstBaseReq<AcTokenOAuthBodyReq> {

    private static final long serialVersionUID = 1L;

    public AcTokenOAuthReq(String devAcCode, AcTokenOAuthBodyReq body) {
        super(devAcCode, body);
    }

    public static class AcTokenOAuthBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 公众号的唯一标识
         */
        @JsonProperty("appid")
        private String appId;

        /**
         * 公众号的appsecret
         */
        @JsonProperty("secret")
        private String appSecret;

        /**
         * 第一步获取的code参数
         */
        @JsonProperty("code")
        private String code;

        public AcTokenOAuthBodyReq() {
            super();
        }

        public AcTokenOAuthBodyReq(String appId, String appSecret, String code) {
            this();
            this.appId = appId;
            this.appSecret = appSecret;
            this.code = code;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppSecret() {
            return appSecret;
        }

        public void setAppSecret(String appSecret) {
            this.appSecret = appSecret;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }

}
