package com.gustz.dove.cpcli.api.customer.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.customer.vo.MediaCust;
import com.sinovatech.rd.wcsb.cli.api.message.vo.CommCpMsg;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.cpcli.api.customer.req.BaseMpNewsCustReq.BaseMpNewsBodyCustReq;

/**
 * 
 * TODO: 基础组合新闻请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class BaseMpNewsCustReq extends CustBaseReq<BaseMpNewsBodyCustReq> {

    private static final long serialVersionUID = 1L;

    public BaseMpNewsCustReq(String devAcCode, BaseMpNewsBodyCustReq body) {
        super(devAcCode, body);
    }

    public static class BaseMpNewsBodyCustReq extends CommCpMsg {

        private static final long serialVersionUID = 1L;

        /**
         * 组合新闻消息对象
         */
        @JsonProperty("mpnews")
        private MediaCust baseMpNews;

        public BaseMpNewsBodyCustReq() {
            super();
            super.setMsgType(MsgTypeDict.MPNEWS);
        }

        public BaseMpNewsBodyCustReq(MediaCust baseMpNews) {
            this();
            this.baseMpNews = baseMpNews;
        }

        public MediaCust getBaseMpNews() {
            return baseMpNews;
        }

        public void setBaseMpNews(MediaCust baseMpNews) {
            this.baseMpNews = baseMpNews;
        }

    }

}
