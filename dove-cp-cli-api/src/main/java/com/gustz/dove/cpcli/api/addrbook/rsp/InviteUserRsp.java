package com.gustz.dove.cpcli.api.addrbook.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.dict.InviteTypeDict;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.cpcli.api.addrbook.rsp.InviteUserRsp.InviteUserBodyRsp;

/**
 * 
 * TODO: 邀请用户关注响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class InviteUserRsp extends AbstBaseRsp<InviteUserBodyRsp> {

    private static final long serialVersionUID = 1L;

    public InviteUserRsp(long sn, String websCode) {
        super(sn, websCode, null, new InviteUserBodyRsp());
    }

    public static class InviteUserBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 1:微信邀请 2.邮件邀请 
         */
        @JsonProperty("type")
        private InviteTypeDict inviteType;

        public InviteUserBodyRsp() {
            super();
        }

        public InviteUserBodyRsp(InviteTypeDict inviteType) {
            super();
            this.inviteType = inviteType;
        }

        public InviteTypeDict getInviteType() {
            return inviteType;
        }

        public void setInviteType(InviteTypeDict inviteType) {
            this.inviteType = inviteType;
        }

    }

}
