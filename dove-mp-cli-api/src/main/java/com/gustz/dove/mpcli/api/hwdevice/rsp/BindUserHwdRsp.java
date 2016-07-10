package com.gustz.dove.mpcli.api.hwdevice.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.mpcli.api.hwdevice.rsp.BindUserHwdRsp.BindUserHwdBodyRsp;

/**
 * 
 * TODO:  用户和设备的绑定/解绑响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class BindUserHwdRsp extends AbstBaseRsp<BindUserHwdBodyRsp> {

    private static final long serialVersionUID = 1L;

    public BindUserHwdRsp(long sn, String websCode) {
        super(sn, websCode, null, new BindUserHwdBodyRsp());
    }

    public static class BindUserHwdBodyRsp extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 响应的消息
         */
        @JsonProperty("base_resp")
        private ErrorBodyRsp baseResp = new ErrorBodyRsp();

        public BindUserHwdBodyRsp() {
            super();
        }

        public BindUserHwdBodyRsp(ErrorBodyRsp baseResp) {
            this();
            this.baseResp = baseResp;
        }

        public ErrorBodyRsp getBaseResp() {
            return baseResp;
        }

        public void setBaseResp(ErrorBodyRsp baseResp) {
            this.baseResp = baseResp;
        }

    }
}
