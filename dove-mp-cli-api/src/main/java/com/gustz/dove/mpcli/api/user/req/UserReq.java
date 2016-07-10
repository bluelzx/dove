package com.gustz.dove.mpcli.api.user.req;

import com.sinovatech.rd.wcsb.cli.api.service.dict.LangTypeDict;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.mpcli.api.user.req.UserReq.UserBodyReq;

/**
 * 
 * TODO: 用户请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class UserReq extends AbstBaseReq<UserBodyReq> {

    private static final long serialVersionUID = 1L;

    public UserReq(String devAcCode, UserBodyReq body) {
        super(devAcCode, body);
    }

    public static class UserBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        // 公众号
        private String openId;

        // 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语 
        private String lang = LangTypeDict.CHINA.getName();

        public UserBodyReq(String openId) {
            super();
            this.openId = openId;
        }

        public UserBodyReq(String openId, String lang) {
            this(openId);
            this.lang = lang;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

    }

}
