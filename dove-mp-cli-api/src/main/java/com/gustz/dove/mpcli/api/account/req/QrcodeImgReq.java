package com.gustz.dove.mpcli.api.account.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.mpcli.api.account.req.QrcodeImgReq.QrcodeImgBodyReq;

/**
 * 
 * TODO: 二维码图片请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class QrcodeImgReq extends AbstBaseReq<QrcodeImgBodyReq> {

    private static final long serialVersionUID = 1L;

    public QrcodeImgReq(String devAcCode, QrcodeImgBodyReq body) {
        super(devAcCode, body);
    }

    public static class QrcodeImgBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 二维码凭证
         */
        @JsonProperty("ticket")
        private String ticket;

        public QrcodeImgBodyReq(String ticket) {
            this.ticket = ticket;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

    }
}
