package com.gustz.dove.cpcli.api.customer.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.customer.vo.MediaCust;
import com.sinovatech.rd.wcsb.cli.api.message.vo.CommCpMsg;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.cpcli.api.customer.req.VoiceCustReq.VoiceBodyCustReq;

/**
 * 
 * TODO: 语音请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class VoiceCustReq extends CustBaseReq<VoiceBodyCustReq> {

    private static final long serialVersionUID = 1L;

    public VoiceCustReq(String devAcCode, VoiceBodyCustReq body) {
        super(devAcCode, body);
    }

    public static class VoiceBodyCustReq extends CommCpMsg {

        private static final long serialVersionUID = 1L;

        /**
         * 语音消息
         */
        @JsonProperty("voice")
        private MediaCust voice;

        public VoiceBodyCustReq() {
            super.setMsgType(MsgTypeDict.VOICE);
        }

        public VoiceBodyCustReq(String toUser, MediaCust voice) {
            this();
            super.setToUser(toUser);
            this.voice = voice;
        }

        public MediaCust getVoice() {
            return voice;
        }

        public void setVoice(MediaCust voice) {
            this.voice = voice;
        }

    }

}
