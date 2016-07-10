package com.gustz.dove.mpcli.api.customer.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.customer.vo.CardCpCust;
import com.sinovatech.rd.wcsb.cli.api.message.vo.CommMsg;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.mpcli.api.customer.req.CardCpCustReq.CardCpBodyCustReq;

/**
 * 
 * TODO: 卡券请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class CardCpCustReq extends CustBaseReq<CardCpBodyCustReq> {

    private static final long serialVersionUID = 1L;

    public CardCpCustReq(String devAcCode, CardCpBodyCustReq body) {
        super(devAcCode, body);
    }

    public static class CardCpBodyCustReq extends CommMsg {

        private static final long serialVersionUID = 1L;

        /**
         * 卡券消息
         */
        @JsonProperty("wxcard")
        private CardCpCust wxcard;

        public CardCpBodyCustReq() {
            super.setMsgType(MsgTypeDict.WXCARD);
        }

        public CardCpBodyCustReq(String toUser, CardCpCust wxcard) {
            this();
            super.setToUser(toUser);
            this.wxcard = wxcard;
        }

        public CardCpCust getWxcard() {
            return wxcard;
        }

        public void setWxcard(CardCpCust wxcard) {
            this.wxcard = wxcard;
        }

    }

}
