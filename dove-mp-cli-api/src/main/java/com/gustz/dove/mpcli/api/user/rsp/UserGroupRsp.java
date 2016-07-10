package com.gustz.dove.mpcli.api.user.rsp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.mpcli.api.user.rsp.UserGroupRsp.UserGroupBodyRsp;
import com.gustz.dove.mpcli.api.user.vo.UserGroup;

/**
 * 
 * TODO: 用户响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class UserGroupRsp extends AbstBaseRsp<UserGroupBodyRsp> {

    private static final long serialVersionUID = 1L;

    public UserGroupRsp(long sn, String websCode) {
        super(sn, websCode, null, new UserGroupBodyRsp());
    }

    public static class UserGroupBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        // 用户群组ID
        @JsonProperty("groupid")
        private String groupId;

        // 用户群组
        @JsonProperty("group")
        private UserGroup userGroup;

        // 用户群组集合
        @JsonProperty("groups")
        private List<UserGroup> userGroups;

        public UserGroupBodyRsp() {
            super();
        }

        public UserGroup getUserGroup() {
            return userGroup;
        }

        public void setUserGroup(UserGroup userGroup) {
            this.userGroup = userGroup;
        }

        public List<UserGroup> getUserGroups() {
            return userGroups;
        }

        public void setUserGroups(List<UserGroup> userGroups) {
            this.userGroups = userGroups;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

    }
}
