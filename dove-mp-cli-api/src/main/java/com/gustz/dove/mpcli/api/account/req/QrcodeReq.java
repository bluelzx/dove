package com.gustz.dove.mpcli.api.account.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.mpcli.api.account.vo.QrcodeInfo;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.mpcli.api.account.req.QrcodeReq.QrcodeBodyReq;
import com.gustz.dove.mpcli.api.account.vo.QrcodeType;

/**
 * 
 * TODO: 二维码请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class QrcodeReq extends AbstBaseReq<QrcodeBodyReq> {

    private static final long serialVersionUID = 1L;

    public QrcodeReq(String devAcCode, QrcodeBodyReq body) {
        super(devAcCode, body);
    }

    public static class QrcodeBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值 
         */
        @JsonProperty("action_name")
        private QrcodeType qrcodeType;

        /**
         * 二维码详细信息
         */
        @JsonProperty("action_info")
        private QrcodeInfo qrcodeInfo;

        public QrcodeBodyReq() {
            super();
        }

        public QrcodeBodyReq(QrcodeType qrcodeType, QrcodeInfo qrcodeInfo) {
            this();
            this.qrcodeType = qrcodeType;
            this.qrcodeInfo = qrcodeInfo;
        }

        public QrcodeType getQrcodeType() {
            return qrcodeType;
        }

        public void setQrcodeType(QrcodeType qrcodeType) {
            this.qrcodeType = qrcodeType;
        }

        public QrcodeInfo getQrcodeInfo() {
            return qrcodeInfo;
        }

        public void setQrcodeInfo(QrcodeInfo qrcodeInfo) {
            this.qrcodeInfo = qrcodeInfo;
        }

    }
}
