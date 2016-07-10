package com.gustz.dove.cli.api.security.req;

import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.cli.api.security.req.AccessTokenReq.AccessTokenBodyReq;
import com.gustz.dove.cli.api.service.vo.AbstBaseReq;

/**
 * 
 * TODO: 接口凭证请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class AccessTokenReq extends AbstBaseReq<AccessTokenBodyReq> {

    private static final long serialVersionUID = 1L;

    public AccessTokenReq(String devAcCode, AccessTokenBodyReq body) {
        super(devAcCode, body);
    }

    public static class AccessTokenBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 公众号
         */
        private String openId;

        public AccessTokenBodyReq(String openId) {
            super();
            this.openId = openId;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }
    }

}
