package com.gustz.dove.mpcli.api.hwdevice.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.mpcli.api.hwdevice.req.BindUserHwdReq.BindUserHwdBodyReq;

/**
 * 
 * TODO:  用户和设备的绑定/解绑请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class BindUserHwdReq extends AbstBaseReq<BindUserHwdBodyReq> {

    private static final long serialVersionUID = 1L;

    public BindUserHwdReq(String devAcCode, BindUserHwdBodyReq body) {
        super(devAcCode, body);
    }

    public static class BindUserHwdBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 用户对应的openId
         */
        @JsonProperty("openid")
        private String openId;

        /**
         * 设备id
         */
        @JsonProperty("device_id")
        private String deviceId;

        /**
         * 绑定操作合法性的凭证（由微信后台生成，第三方H5通过客户端jsapi获得） 
         */
        @JsonProperty("ticket")
        private String ticket;

        public BindUserHwdBodyReq() {
            super();
        }

        public BindUserHwdBodyReq(String openId, String deviceId) {
            this();
            this.openId = openId;
            this.deviceId = deviceId;
        }

        public BindUserHwdBodyReq(String openId, String deviceId, String ticket) {
            this(openId, deviceId);
            this.ticket = ticket;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
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
