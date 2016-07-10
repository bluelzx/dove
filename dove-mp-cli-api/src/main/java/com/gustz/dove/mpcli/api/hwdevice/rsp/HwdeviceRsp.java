package com.gustz.dove.mpcli.api.hwdevice.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.mpcli.api.hwdevice.vo.HwdBaseInfo;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.mpcli.api.hwdevice.rsp.HwdeviceRsp.HwdeviceBodyRsp;

/**
 * 
 * TODO: 硬件设备信息响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class HwdeviceRsp extends AbstBaseRsp<HwdeviceBodyRsp> {

    private static final long serialVersionUID = 1L;

    public HwdeviceRsp(long sn, String websCode) {
        super(sn, websCode, null, new HwdeviceBodyRsp());
    }

    public static class HwdeviceBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 设备id授权的response（json数组形式） 
         */
        @JsonProperty("resp")
        private HwdevicesBaseRsp[] resp;

        public HwdeviceBodyRsp() {
            super();
        }

        public HwdeviceBodyRsp(HwdevicesBaseRsp[] resp) {
            this();
            this.resp = resp;
        }

        public HwdevicesBaseRsp[] getResp() {
            return resp;
        }

        public void setResp(HwdevicesBaseRsp[] resp) {
            this.resp = resp;
        }

        public static class HwdevicesBaseRsp extends ErrorBodyRsp {

            private static final long serialVersionUID = 1L;

            /**
             * 设备基本信息（包括device typ和device id，目前device type为用户的原始id）
             */
            @JsonProperty("base_info")
            private HwdBaseInfo[] baseInfo;

            public HwdevicesBaseRsp() {
                super();
            }

            public HwdevicesBaseRsp(HwdBaseInfo[] baseInfo) {
                this();
                this.baseInfo = baseInfo;
            }

            public HwdBaseInfo[] getBaseInfo() {
                return baseInfo;
            }

            public void setBaseInfo(HwdBaseInfo[] baseInfo) {
                this.baseInfo = baseInfo;
            }
        }
    }

}