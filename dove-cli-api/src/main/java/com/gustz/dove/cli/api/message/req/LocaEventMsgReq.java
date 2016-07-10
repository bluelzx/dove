package com.gustz.dove.cli.api.message.req;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.dict.EventTypeDict;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 上报地理位置事件
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ] 
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocaEventMsgReq extends EventMsgReq<LocaEventMsgReq> {

    private static final long serialVersionUID = 1L;

    /**
     * 地理位置纬度
     */
    @JsonProperty("Latitude")
    @XmlElement(name = "Latitude")
    private double latitude;

    /**
     * 地理位置经度
     */
    @JsonProperty("Longitude")
    @XmlElement(name = "Longitude")
    private double longitude;

    /**
     * 地理位置精度 
     */
    @JsonProperty("Precision")
    @XmlElement(name = "Precision")
    private double precision;

    public LocaEventMsgReq() {
        super();
        super.setMsgType(MsgTypeDict.EVENT);
        super.setEvent(EventTypeDict.LOCATION);
    }

    public LocaEventMsgReq(double latitude, double longitude, double precision) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.precision = precision;
    }

    public static LocaEventMsgReq toBean(String xml) throws JAXBException {
        return new LocaEventMsgReq().toBean(xml, LocaEventMsgReq.class);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

}
