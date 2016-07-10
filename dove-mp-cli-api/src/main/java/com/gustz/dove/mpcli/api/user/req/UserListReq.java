package com.gustz.dove.mpcli.api.user.req;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.dict.LangTypeDict;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.mpcli.api.user.req.UserListReq.UserListBodyReq;

/**
 * 
 * TODO: 用户集请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class UserListReq extends AbstBaseReq<UserListBodyReq> {

    private static final long serialVersionUID = 1L;

    public UserListReq(String devAcCode, UserListBodyReq body) {
        super(devAcCode, body);
    }

    public static class UserListBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 用户集合
         */
        @JsonProperty("user_list")
        private List<SimpleUser> userList;

        public UserListBodyReq(List<SimpleUser> userList) {
            this.userList = userList;
        }

        public List<SimpleUser> getUserList() {
            return userList;
        }

        public void setUserList(List<SimpleUser> userList) {
            this.userList = userList;
        }

        public static class SimpleUser implements Serializable {

            private static final long serialVersionUID = 1L;

            /**
             * 用户的标识，对当前公众号唯一 
             */
            @JsonProperty("openid")
            private String openId;

            /**
             * 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为zh-CN 
             */
            @JsonProperty("lang")
            private LangTypeDict lang;

            public SimpleUser(String openId, LangTypeDict lang) {
                super();
                this.openId = openId;
                this.lang = lang;
            }

            public String getOpenId() {
                return openId;
            }

            public void setOpenId(String openId) {
                this.openId = openId;
            }

            public LangTypeDict getLang() {
                return lang;
            }

            public void setLang(LangTypeDict lang) {
                this.lang = lang;
            }

        }
    }

}
