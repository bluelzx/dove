package com.gustz.dove.mpcli.api.hwdevice.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.mpcli.api.hwdevice.rsp.HwdQrcodeRsp.HwdQrcodeBodyRsp;

/**
 * 
 * TODO: 硬件设备二维码响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class HwdQrcodeRsp extends AbstBaseRsp<HwdQrcodeBodyRsp> {

    private static final long serialVersionUID = 1L;

    public HwdQrcodeRsp(long sn, String websCode) {
        super(sn, websCode, null, new HwdQrcodeBodyRsp());
    }

    public static class HwdQrcodeBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 成功生成二维码的数量
         */
        @JsonProperty("device_num")
        private int deviceNum;

        /**
         * 二维码列表（json的数组形式）当errcode为0且device_num不为0时数组才有内容
         */
        @JsonProperty("code_list")
        private HwdQrcodeCodeList[] codeList;

        public HwdQrcodeBodyRsp() {
            super();
        }

        public HwdQrcodeBodyRsp(int deviceNum, HwdQrcodeCodeList[] codeList) {
            this();
            this.deviceNum = deviceNum;
            this.codeList = codeList;
        }

        public int getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(int deviceNum) {
            this.deviceNum = deviceNum;
        }

        public HwdQrcodeCodeList[] getCodeList() {
            return codeList;
        }

        public void setCodeList(HwdQrcodeCodeList[] codeList) {
            this.codeList = codeList;
        }

        public static class HwdQrcodeCodeList extends AbstCliBaseVo {

            private static final long serialVersionUID = 1L;

            /**
             *设备id
             */
            @JsonProperty("device_id")
            private String deviceId;

            /**
             *设备id对应的二维码生成串 
             */
            @JsonProperty("ticket")
            private String ticket;

            public HwdQrcodeCodeList() {
                super();
            }

            public HwdQrcodeCodeList(String deviceId, String ticket) {
                this();
                this.deviceId = deviceId;
                this.ticket = ticket;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getTicket() {
                return ticket;
            }

            public void setTicket(String ticket) {
                this.ticket = ticket;
            }
        }

    }

}