package com.gustz.dove.mpcli.api.hwdevice.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ExtErrorBodyRsp;
import com.gustz.dove.mpcli.api.hwdevice.rsp.BindHwdeviceRsp.BindHwdeviceBodyRsp;
import com.gustz.dove.mpcli.api.hwdevice.vo.HwdBaseInfo;

/**
 * 
 * TODO:  用户绑定的设备响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class BindHwdeviceRsp extends AbstBaseRsp<BindHwdeviceBodyRsp> {

    private static final long serialVersionUID = 1L;

    public BindHwdeviceRsp(long sn, String websCode) {
        super(sn, websCode, null, new BindHwdeviceBodyRsp());
    }

    public static class BindHwdeviceBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 微信号
         */
        @JsonProperty("openid")
        private String openId;

        /**
         * 响应的消息
         */
        @JsonProperty("resp_msg")
        private ExtErrorBodyRsp respMsg = new ExtErrorBodyRsp();

        /**
         * 设备列表
         */
        @JsonProperty("device_list")
        private HwdBaseInfo[] deviceList;

        public BindHwdeviceBodyRsp() {
            super();
        }

        public ExtErrorBodyRsp getRespMsg() {
            return respMsg;
        }

        public void setRespMsg(ExtErrorBodyRsp respMsg) {
            this.respMsg = respMsg;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public HwdBaseInfo[] getDeviceList() {
            return deviceList;
        }

        public void setDeviceList(HwdBaseInfo[] deviceList) {
            this.deviceList = deviceList;
        }

    }
}
