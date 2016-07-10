package com.gustz.dove.cli.api.security.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.cli.api.security.rsp.CallbackIpRsp.CallbackIpBodyRsp;
import com.gustz.dove.cli.api.service.vo.AbstBaseRsp;

/**
 * 
 * TODO: 微信服务器IP响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class CallbackIpRsp extends AbstBaseRsp<CallbackIpBodyRsp> {

    private static final long serialVersionUID = 1L;

    public CallbackIpRsp(long sn, String websCode) {
        super(sn, websCode, null, new CallbackIpBodyRsp());
    }

    public static class CallbackIpBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 微信服务器IP地址列表
         */
        @JsonProperty("ip_list")
        private String[] ipList = new String[] {};

        public CallbackIpBodyRsp() {
            super();
        }

        public CallbackIpBodyRsp(String[] ipList) {
            this();
            this.ipList = ipList;
        }

        public String[] getIpList() {
            return ipList;
        }

        public void setIpList(String[] ipList) {
            this.ipList = ipList;
        }
    }

}
