package com.gustz.dove.mpcli.api.hwdevice.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.mpcli.api.hwdevice.vo.Hwdevice;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.mpcli.api.hwdevice.req.HwdeviceReq.HwdeviceBodyReq;

/**
 * 
 * TODO: 硬件设备信息请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class HwdeviceReq extends AbstBaseReq<HwdeviceBodyReq> {

    private static final long serialVersionUID = 1L;

    public HwdeviceReq(String devAcCode, HwdeviceBodyReq body) {
        super(devAcCode, body);
    }

    public static class HwdeviceBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 设备id的个数
         */
        @JsonProperty("device_num")
        private int deviceNum = this.getDeviceList().length;

        /**
         * 设备列表，json的array格式，其size必须等于device_num
         */
        @JsonProperty("device_list")
        private Hwdevice[] deviceList = new Hwdevice[] {};

        /**
         * 请求操作的类型，限定取值为：0：设备授权（缺省值为0） 1：设备更新（更新已授权设备的各属性值） 
         */
        @JsonProperty("op_type")
        private String opType;

        public HwdeviceBodyReq() {
            super();
        }

        public HwdeviceBodyReq(int deviceNum, Hwdevice[] deviceList) {
            this();
            this.deviceNum = deviceNum;
            this.deviceList = deviceList;
        }

        public int getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(int deviceNum) {
            this.deviceNum = deviceNum;
        }

        public Hwdevice[] getDeviceList() {
            return deviceList;
        }

        public void setDeviceList(Hwdevice[] deviceList) {
            this.deviceList = deviceList;
        }

        public String getOpType() {
            return opType;
        }

        public void setOpType(String opType) {
            this.opType = opType;
        }

    }
}
