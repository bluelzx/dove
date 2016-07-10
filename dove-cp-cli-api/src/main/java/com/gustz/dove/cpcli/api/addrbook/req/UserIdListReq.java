package com.gustz.dove.cpcli.api.addrbook.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.cpcli.api.addrbook.req.UserIdListReq.UserIdListBodyReq;

/**
 * 
 * TODO: 用户ID集请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class UserIdListReq extends AbstBaseReq<UserIdListBodyReq> {

    private static final long serialVersionUID = 1L;

    public UserIdListReq(String devAcCode, UserIdListBodyReq body) {
        super(devAcCode, body);
    }

    public static class UserIdListBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 用户集合
         */
        @JsonProperty("useridlist")
        private String[] userIdList;

        public UserIdListBodyReq(String[] userIdList) {
            super();
            this.userIdList = userIdList;
        }

        public String[] getUserIdList() {
            return userIdList;
        }

        public void setUserIdList(String[] userIdList) {
            this.userIdList = userIdList;
        }

    }

}
