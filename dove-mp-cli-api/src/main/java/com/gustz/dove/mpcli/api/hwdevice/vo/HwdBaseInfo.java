package com.gustz.dove.mpcli.api.hwdevice.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 硬件设备基本信息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class HwdBaseInfo extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 设备ID
     */
    @JsonProperty("device_id")
    private String deviceId;

    /**
     * 设备类型为用户的原始id
     */
    @JsonProperty("device_type")
    private String deviceType;

    public HwdBaseInfo() {
        super();
    }

    public HwdBaseInfo(String deviceId, String deviceType) {
        this();
        this.deviceId = deviceId;
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
