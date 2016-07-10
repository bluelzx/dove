package com.gustz.dove.mpcli.api.user.rsp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.mpcli.api.user.rsp.UserListRsp.UserListBodyRsp;
import com.gustz.dove.mpcli.api.user.vo.MpUser;

/**
 * 
 * TODO: 用户集响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class UserListRsp extends AbstBaseRsp<UserListBodyRsp> {

    private static final long serialVersionUID = 1L;

    public UserListRsp(long sn, String websCode) {
        super(sn, websCode, null, new UserListBodyRsp());
    }

    public static class UserListBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 用户信息集合
         */
        @JsonProperty("user_info_list")
        private List<MpUser> userList;

        public UserListBodyRsp() {
            super();
        }

        public List<MpUser> getUserList() {
            return userList;
        }

        public void setUserList(List<MpUser> userList) {
            this.userList = userList;
        }

    }

}
