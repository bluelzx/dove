package com.gustz.dove.mpcli.api.customer.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.customer.vo.VideoCust;
import com.sinovatech.rd.wcsb.cli.api.message.vo.CommMsg;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.mpcli.api.customer.req.VideoCustReq.VideoBodyCustReq;

/**
 * 
 * TODO: 视频请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class VideoCustReq extends CustBaseReq<VideoBodyCustReq> {

    private static final long serialVersionUID = 1L;

    public VideoCustReq(String devAcCode, VideoBodyCustReq body) {
        super(devAcCode, body);
    }

    public static class VideoBodyCustReq extends CommMsg {

        private static final long serialVersionUID = 1L;

        /**
         * 视频消息对象
         */
        @JsonProperty("video")
        private VideoCust video;

        public VideoBodyCustReq() {
            super();
            super.setMsgType(MsgTypeDict.VIDEO);
        }

        public VideoBodyCustReq(VideoCust video) {
            this();
            this.video = video;
        }

        public VideoCust getVideo() {
            return video;
        }

        public void setVideo(VideoCust video) {
            this.video = video;
        }
    }
}
