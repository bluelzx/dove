package com.gustz.dove.mpcli.api.customer.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.customer.vo.MusicCust;
import com.sinovatech.rd.wcsb.cli.api.message.vo.CommMsg;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.mpcli.api.customer.req.MusicCustReq.MusicBodyCustReq;

/**
 * 
 * TODO: 音乐请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class MusicCustReq extends CustBaseReq<MusicBodyCustReq> {

    private static final long serialVersionUID = 1L;

    public MusicCustReq(String devAcCode, MusicBodyCustReq body) {
        super(devAcCode, body);
    }

    public static class MusicBodyCustReq extends CommMsg {

        private static final long serialVersionUID = 1L;

        /**
         * 音乐对象
         */
        @JsonProperty("music")
        private MusicCust music;

        public MusicBodyCustReq() {
            super.setMsgType(MsgTypeDict.MUSIC);
        }

        public MusicBodyCustReq(MusicCust music) {
            this();
            this.music = music;
        }

        public MusicCust getMusic() {
            return music;
        }

        public void setMusic(MusicCust music) {
            this.music = music;
        }
    }

}
