package com.gustz.dove.mpcli.api.user.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.mpcli.api.user.req.UserGroupReq.UserGroupBodyReq;
import com.gustz.dove.mpcli.api.user.vo.UserGroup;

/**
 * 
 * TODO: 用户群组请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class UserGroupReq extends AbstBaseReq<UserGroupBodyReq> {

    private static final long serialVersionUID = 1L;

    public UserGroupReq(String devAcCode, UserGroupBodyReq body) {
        super(devAcCode, body);
    }

    public static class UserGroupBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        // 用户群组ID（用户新群组ID） 
        @JsonProperty("to_groupid")
        private String toGroupId;

        // 用户公众号
        @JsonProperty("openid")
        private String openId;

        // 用户群组
        @JsonProperty("group")
        private UserGroup userGroup;

        public UserGroupBodyReq(UserGroup userGroup) {
            this.userGroup = userGroup;
        }

        public UserGroupBodyReq(UserGroup userGroup, String openId) {
            this(userGroup);
            this.openId = openId;
        }

        public UserGroup getUserGroup() {
            return userGroup;
        }

        public void setUserGroup(UserGroup userGroup) {
            this.userGroup = userGroup;
        }

        public String getToGroupId() {
            return toGroupId;
        }

        public void setToGroupId(String toGroupId) {
            this.toGroupId = toGroupId;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

    }

}
