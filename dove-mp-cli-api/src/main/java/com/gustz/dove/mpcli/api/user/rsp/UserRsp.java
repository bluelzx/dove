package com.gustz.dove.mpcli.api.user.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.gustz.dove.mpcli.api.user.rsp.UserRsp.UserBodyRsp;
import com.gustz.dove.mpcli.api.user.vo.MpUser;

/**
 * 
 * TODO: 用户响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class UserRsp extends AbstBaseRsp<UserBodyRsp> {

    private static final long serialVersionUID = 1L;

    public UserRsp(long sn, String websCode) {
        super(sn, websCode, null, new UserBodyRsp());
    }

    public static class UserBodyRsp extends MpUser {

        private static final long serialVersionUID = 1L;

        /**
         * 错误编码--特殊的
         */
        @JsonProperty("errcode")
        private int errCode;

        /**
         * 错误提示--特殊的
         */
        @JsonProperty("errmsg")
        private String errMsg;

        public UserBodyRsp() {
            super();
        }

        public int getErrCode() {
            return errCode;
        }

        public void setErrCode(int errCode) {
            this.errCode = errCode;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

    }

}
