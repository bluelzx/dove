package com.gustz.dove.mpcli.api.customer.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.customer.vo.TextCust;
import com.sinovatech.rd.wcsb.cli.api.message.vo.CommMsg;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.mpcli.api.customer.req.TextCustReq.TextBodyCustReq;

/**
 * 
 * TODO: 文本请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class TextCustReq extends CustBaseReq<TextBodyCustReq> {

    private static final long serialVersionUID = 1L;

    public TextCustReq(String devAcCode, TextBodyCustReq body) {
        super(devAcCode, body);
    }

    public static class TextBodyCustReq extends CommMsg {

        private static final long serialVersionUID = 1L;

        /**
         * 文本消息对象
         */
        @JsonProperty("text")
        private TextCust text;

        public TextBodyCustReq() {
            super();
            super.setMsgType(MsgTypeDict.TEXT);
        }

        public TextBodyCustReq(String toUser, TextCust text) {
            this();
            super.setToUser(toUser);
            this.text = text;
        }

        public TextCust getText() {
            return text;
        }

        public void setText(TextCust text) {
            this.text = text;
        }
    }

}
