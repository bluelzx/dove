package com.gustz.dove.cpcli.api.addrbook.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.cpcli.api.addrbook.rsp.DeptListRsp.UserDeptListBodyRsp;
import com.gustz.dove.cpcli.api.addrbook.vo.Department;

/**
 * 
 * TODO: 用户部门集响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class DeptListRsp extends AbstBaseRsp<UserDeptListBodyRsp> {

    private static final long serialVersionUID = 1L;

    public DeptListRsp(long sn, String websCode) {
        super(sn, websCode, null, new UserDeptListBodyRsp());
    }

    public static class UserDeptListBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 部门列表数据。以部门的order字段从小到大排列
         */
        @JsonProperty("department")
        private Department[] departments;

        public UserDeptListBodyRsp() {
            super();
        }

        public UserDeptListBodyRsp(Department[] userDepts) {
            super();
            this.departments = userDepts;
        }

        public Department[] getUserDepts() {
            return departments;
        }

        public void setUserDepts(Department[] userDepts) {
            this.departments = userDepts;
        }

    }
}
