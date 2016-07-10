package com.gustz.dove.cpcli.api.customer.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.customer.vo.MpNewsCust;
import com.sinovatech.rd.wcsb.cli.api.message.vo.CommCpMsg;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.cpcli.api.customer.req.MpNewsCustReq.MpNewsBodyCustReq;

/**
 * 
 * TODO: 组合新闻请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class MpNewsCustReq extends CustBaseReq<MpNewsBodyCustReq> {

    private static final long serialVersionUID = 1L;

    public MpNewsCustReq(String devAcCode, MpNewsBodyCustReq body) {
        super(devAcCode, body);
    }

    public static class MpNewsBodyCustReq extends CommCpMsg {

        private static final long serialVersionUID = 1L;

        /**
         * 组合新闻消息对象
         */
        @JsonProperty("mpnews")
        private MpNewsCust mpNews;

        public MpNewsBodyCustReq() {
            super();
            super.setMsgType(MsgTypeDict.MPNEWS);
        }

        public MpNewsBodyCustReq(MpNewsCust mpNews) {
            this();
            this.mpNews = mpNews;
        }

        public MpNewsCust getMpNews() {
            return mpNews;
        }

        public void setMpNews(MpNewsCust mpNews) {
            this.mpNews = mpNews;
        }

    }

}
