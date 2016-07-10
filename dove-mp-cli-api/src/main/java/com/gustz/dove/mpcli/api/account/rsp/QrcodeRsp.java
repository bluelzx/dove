package com.gustz.dove.mpcli.api.account.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.mpcli.api.account.rsp.QrcodeRsp.QrcodeBodyRsp;

/**
 * 
 * TODO: 二维码响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class QrcodeRsp extends AbstBaseRsp<QrcodeBodyRsp> {

    private static final long serialVersionUID = 1L;

    public QrcodeRsp(long sn, String websCode) {
        super(sn, websCode, null, new QrcodeBodyRsp());
    }

    public static class QrcodeBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 二维码凭证
         */
        @JsonProperty("ticket")
        private String ticket;

        /**
         * 二维码的有效时间，以秒为单位。最大不超过1800。  
         */
        @JsonProperty("expire_seconds")
        private long expireSeconds;

        /**
         * 二维码图片解析后的地址
         */
        @JsonProperty("url")
        private String url;

        public QrcodeBodyRsp() {
            super();
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

    }

}