package com.gustz.dove.cpcli.api.addrbook.rsp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.cpcli.api.addrbook.rsp.UserListRsp.UserListBodyRsp;
import com.gustz.dove.cpcli.api.addrbook.vo.CpUser;

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
        @JsonProperty("userlist")
        private List<CpUser> userList;

        public UserListBodyRsp() {
            super();
        }

        public List<CpUser> getUserList() {
            return userList;
        }

        public void setUserList(List<CpUser> userList) {
            this.userList = userList;
        }

    }

}
