package com.gustz.dove.mpcli.api.hwdevice.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ExtErrorBodyRsp;
import com.gustz.dove.mpcli.api.hwdevice.rsp.HwdBindOpenIdRsp.HwdBindOpenIdBodyRsp;

/**
 * 
 * TODO:  设备绑定OpenID响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class HwdBindOpenIdRsp extends AbstBaseRsp<HwdBindOpenIdBodyRsp> {

    private static final long serialVersionUID = 1L;

    public HwdBindOpenIdRsp(long sn, String websCode) {
        super(sn, websCode, null, new HwdBindOpenIdBodyRsp());
    }

    public static class HwdBindOpenIdBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 微信号
         */
        @JsonProperty("open_id")
        private String[] openId = new String[] {};

        /**
         * 响应的消息
         */
        @JsonProperty("resp_msg")
        private ExtErrorBodyRsp respMsg;

        public HwdBindOpenIdBodyRsp() {
            super();
        }

        public String[] getOpenId() {
            return openId;
        }

        public void setOpenId(String[] openId) {
            this.openId = openId;
        }

        public ExtErrorBodyRsp getRespMsg() {
            return respMsg;
        }

        public void setRespMsg(ExtErrorBodyRsp respMsg) {
            this.respMsg = respMsg;
        }

    }
}
