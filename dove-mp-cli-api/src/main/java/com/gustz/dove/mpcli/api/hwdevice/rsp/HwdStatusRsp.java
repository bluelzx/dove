package com.gustz.dove.mpcli.api.hwdevice.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.mpcli.api.hwdevice.rsp.HwdStatusRsp.HwdStatusBodyRsp;

/**
 * 
 * TODO: 硬件设备状态响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class HwdStatusRsp extends AbstBaseRsp<HwdStatusBodyRsp> {

    private static final long serialVersionUID = 1L;

    public HwdStatusRsp(long sn, String websCode) {
        super(sn, websCode, null, new HwdStatusBodyRsp());
    }

    public static class HwdStatusBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 设备状态，目前取值如下： 0：未授权 1：已经授权（尚未被用户绑定） <br/>
         * 2：已经被用户绑定 3：属性未设置  <br/>
         */
        @JsonProperty("status")
        private int status;

        /**
         * status对应的描述
         */
        @JsonProperty("status_info")
        private String statusInfo = "bind";

        public HwdStatusBodyRsp() {
            super();
        }

        public HwdStatusBodyRsp(int status, String statusInfo) {
            this();
            this.status = status;
            this.statusInfo = statusInfo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusInfo() {
            return statusInfo;
        }

        public void setStatusInfo(String statusInfo) {
            this.statusInfo = statusInfo;
        }

    }

}