package com.gustz.dove.cli.api.message.req;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 地理位置请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationMsgReq extends MsgBaseReq<LocationMsgReq> {

    private static final long serialVersionUID = 1L;

    /**
     * 地理位置维度
     */
    @JsonProperty("Location_X")
    @XmlElement(name = "Location_X")
    private String locationX;

    /**
     * 地理位置经度
     */
    @JsonProperty("Location_Y")
    @XmlElement(name = "Location_Y")
    private String locationY;

    /**
     * 地图缩放大小
     */
    @JsonProperty("Scale")
    @XmlElement(name = "Scale")
    private String scale;

    /**
     * 地理位置信息
     */
    @JsonProperty("Label")
    @XmlElement(name = "Label")
    private String label;

    public LocationMsgReq() {
        super.setMsgType(MsgTypeDict.LOCATION);
    }

    public LocationMsgReq(String locationX, String locationY, String scale, String label) {
        this();
        this.locationX = locationX;
        this.locationY = locationY;
        this.scale = scale;
        this.label = label;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static LocationMsgReq toBean(String xml) throws JAXBException {
        return new LocationMsgReq().toBean(xml, LocationMsgReq.class);
    }

}
