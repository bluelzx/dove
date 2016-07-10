package com.gustz.dove.cpcli.api.customer.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.cpcli.api.customer.rsp.CommCustRsp.CommBodyCustRsp;

/**
 * TODO: Common customer response 
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 10, 2015 ]
 */
public class CommCustRsp extends AbstBaseRsp<CommBodyCustRsp> {

    private static final long serialVersionUID = 1L;

    public CommCustRsp(long sn, String websCode) {
        super(sn, websCode, null, new CommBodyCustRsp());
    }

    public static class CommBodyCustRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 无效用户
         */
        @JsonProperty("invaliduser")
        private String invalidUser;
        private String[] invalidUsers;

        /**
         * 无效部门
         */
        @JsonProperty("invalidparty")
        private String invalidDept;

        /**
         * 无效标签
         */
        @JsonProperty("invalidtag")
        private String invalidTag;

        public CommBodyCustRsp() {
            super();
        }

        public void setInvalidUser(String invalidUser) {
            //this.invalidUser = invalidUser;
            if (invalidUser != null && !invalidUser.isEmpty()) {
                this.setInvalidUsers(invalidUser.split("\\|"));
            }
        }

        ///////////////////////////////////////
        public String getInvalidUser() {
            return invalidUser;
        }

        public String[] getInvalidUsers() {
            return invalidUsers;
        }

        public void setInvalidUsers(String[] invalidUsers) {
            this.invalidUsers = invalidUsers;
        }

        public String getInvalidDept() {
            return invalidDept;
        }

        public String getInvalidTag() {
            return invalidTag;
        }

    }
}
