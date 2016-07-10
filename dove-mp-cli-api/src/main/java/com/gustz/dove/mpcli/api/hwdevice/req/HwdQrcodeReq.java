package com.gustz.dove.mpcli.api.hwdevice.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.mpcli.api.hwdevice.req.HwdQrcodeReq.HwdQrcodeBodyReq;

/**
 * 
 * TODO: 硬件设备二维码请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class HwdQrcodeReq extends AbstBaseReq<HwdQrcodeBodyReq> {

    private static final long serialVersionUID = 1L;

    public HwdQrcodeReq(String devAcCode, HwdQrcodeBodyReq body) {
        super(devAcCode, body);
    }

    public static class HwdQrcodeBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 设备id的个数
         */
        @JsonProperty("device_num")
        private int deviceNum = this.getDeviceIdList().length;

        /**
         * 设备id的列表，json的array格式，其size必须等于device_num
         */
        @JsonProperty("device_id_list")
        private String[] deviceIdList = new String[] {};

        public HwdQrcodeBodyReq() {
            super();
        }

        public HwdQrcodeBodyReq(int deviceNum, String[] deviceIdList) {
            this();
            this.deviceNum = deviceNum;
            this.deviceIdList = deviceIdList;
        }

        public int getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(int deviceNum) {
            this.deviceNum = deviceNum;
        }

        public String[] getDeviceIdList() {
            return deviceIdList;
        }

        public void setDeviceIdList(String[] deviceIdList) {
            this.deviceIdList = deviceIdList;
        }

    }
}
