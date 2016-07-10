package com.gustz.dove.cpcli.api.addrbook.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.cpcli.api.addrbook.req.InviteUserReq.InviteUserBodyReq;

/**
 * 
 * TODO: 邀请用户关注请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class InviteUserReq extends AbstBaseReq<InviteUserBodyReq> {

    private static final long serialVersionUID = 1L;

    public InviteUserReq(String devAcCode, InviteUserBodyReq body) {
        super(devAcCode, body);
    }

    public static class InviteUserBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 成员UserID。对应管理端的帐号
         */
        @JsonProperty("userid")
        private String userId;

        public InviteUserBodyReq(String userId) {
            super();
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

    }

}
