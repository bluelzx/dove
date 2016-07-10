package com.gustz.dove.cpcli.api.addrbook.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.cpcli.api.addrbook.rsp.DepartmentRsp.UserDeptBodyRsp;

/**
 * 
 * TODO: 用户部门响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class DepartmentRsp extends AbstBaseRsp<UserDeptBodyRsp> {

    private static final long serialVersionUID = 1L;

    public DepartmentRsp(long sn, String websCode) {
        super(sn, websCode, null, new UserDeptBodyRsp());
    }

    public static class UserDeptBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 用户部门ID
         */
        @JsonProperty("id")
        private int id;

        public UserDeptBodyRsp() {
            super();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }
}
