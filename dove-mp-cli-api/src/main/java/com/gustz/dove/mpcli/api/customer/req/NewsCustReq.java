package com.gustz.dove.mpcli.api.customer.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.customer.req.CustBaseReq;
import com.sinovatech.rd.wcsb.cli.api.customer.vo.NewsCust;
import com.sinovatech.rd.wcsb.cli.api.message.vo.CommMsg;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.mpcli.api.customer.req.NewsCustReq.NewsBodyCustReq;

/**
 * 
 * TODO: 新闻请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class NewsCustReq extends CustBaseReq<NewsBodyCustReq> {

    private static final long serialVersionUID = 1L;

    public NewsCustReq(String devAcCode, NewsBodyCustReq body) {
        super(devAcCode, body);
    }

    public static class NewsBodyCustReq extends CommMsg {

        private static final long serialVersionUID = 1L;

        /**
         * 新闻消息对象
         */
        @JsonProperty("news")
        private NewsCust news;

        public NewsBodyCustReq() {
            super();
            super.setMsgType(MsgTypeDict.NEWS);
        }

        public NewsBodyCustReq(NewsCust news) {
            this();
            this.news = news;
        }

        public NewsCust getNews() {
            return news;
        }

        public void setNews(NewsCust news) {
            this.news = news;
        }
    }

}
