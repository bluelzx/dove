package com.gustz.dove.cpcli.api.addrbook.req;

import com.sinovatech.rd.wcsb.cli.api.service.dict.UserStatusDict;
import com.sinovatech.rd.wcsb.cli.api.service.dict.YnDict;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.cpcli.api.addrbook.req.UserBaseReq.UserBaseBodyReq;

/**
 * 
 * TODO: 用户基础请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class UserBaseReq extends AbstBaseReq<UserBaseBodyReq> {

    private static final long serialVersionUID = 1L;

    public UserBaseReq(String devAcCode, UserBaseBodyReq body) {
        super(devAcCode, body);
    }

    public static class UserBaseBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        // 部门ID
        private String deptId;

        // 1/0：是否递归获取子部门下面的成员
        private YnDict fetchChild = YnDict.Y;

        // 0获取全部成员，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。
        private UserStatusDict state = UserStatusDict.ALL;

        public UserBaseBodyReq() {
            super();
        }

        public UserBaseBodyReq(String deptId) {
            super();
            this.deptId = deptId;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public YnDict getFetchChild() {
            return fetchChild;
        }

        public void setFetchChild(YnDict fetchChild) {
            this.fetchChild = fetchChild;
        }

        public UserStatusDict getState() {
            return state;
        }

        public void setState(UserStatusDict state) {
            this.state = state;
        }

    }

}
