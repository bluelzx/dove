package com.gustz.dove.mpcli.api.customer.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.customer.vo.MediaCust;
import com.sinovatech.rd.wcsb.cli.api.message.vo.CommMsg;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.mpcli.api.customer.req.ImageCustReq.ImageBodyCustReq;

/**
 * 
 * TODO: 图片请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class ImageCustReq extends CustBaseReq<ImageBodyCustReq> {

    private static final long serialVersionUID = 1L;

    public ImageCustReq(String devAcCode, ImageBodyCustReq body) {
        super(devAcCode, body);
    }

    public static class ImageBodyCustReq extends CommMsg {

        private static final long serialVersionUID = 1L;

        /**
         * 图片消息
         */
        @JsonProperty("image")
        private MediaCust image;

        public ImageBodyCustReq() {
            super.setMsgType(MsgTypeDict.IMAGE);
        }

        public ImageBodyCustReq(String toUser, MediaCust image) {
            this();
            super.setToUser(toUser);
            this.image = image;
        }

        public MediaCust getImage() {
            return image;
        }

        public void setImage(MediaCust image) {
            this.image = image;
        }

    }

}
