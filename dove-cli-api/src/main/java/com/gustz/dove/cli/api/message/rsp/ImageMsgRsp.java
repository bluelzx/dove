package com.gustz.dove.cli.api.message.rsp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.message.vo.Image;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 图片响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImageMsgRsp extends MsgBaseRsp<ImageMsgRsp> {

    private static final long serialVersionUID = 1L;

    /**
     * 图片
     */
    @XmlElement(name = "Image")
    @JsonProperty("Image")
    private Image image;

    public ImageMsgRsp() {
        super();
        super.setMsgType(MsgTypeDict.IMAGE);
    }

    public ImageMsgRsp(Image image) {
        this();
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
