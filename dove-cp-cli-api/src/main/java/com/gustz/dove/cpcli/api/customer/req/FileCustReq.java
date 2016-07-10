package com.gustz.dove.cpcli.api.customer.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.customer.vo.MediaCust;
import com.sinovatech.rd.wcsb.cli.api.message.vo.CommCpMsg;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.cpcli.api.customer.req.FileCustReq.VoiceBodyCustReq;

/**
 * 
 * TODO: 文件请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class FileCustReq extends CustBaseReq<VoiceBodyCustReq> {

    private static final long serialVersionUID = 1L;

    public FileCustReq(String devAcCode, VoiceBodyCustReq body) {
        super(devAcCode, body);
    }

    public static class VoiceBodyCustReq extends CommCpMsg {

        private static final long serialVersionUID = 1L;

        /**
         * 文件消息
         */
        @JsonProperty("file")
        private MediaCust file;

        public VoiceBodyCustReq() {
            super.setMsgType(MsgTypeDict.FILE);
        }

        public VoiceBodyCustReq(String toUser, MediaCust file) {
            this();
            super.setToUser(toUser);
            this.file = file;
        }

        public MediaCust getFile() {
            return file;
        }

        public void setFile(MediaCust file) {
            this.file = file;
        }

    }

}
