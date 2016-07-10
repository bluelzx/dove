package com.gustz.dove.cpcli.api.agent.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.gustz.dove.cpcli.api.agent.req.AgentAppReq.AgentAppBodyReq;

/**
 * 
 * TODO: 代理应用请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class AgentAppReq extends AbstBaseReq<AgentAppBodyReq> {

    private static final long serialVersionUID = 1L;

    public AgentAppReq(String devAcCode, AgentAppBodyReq body) {
        super(devAcCode, body);
    }

    public static class AgentAppBodyReq extends AbstCliBaseVo {

        private static final long serialVersionUID = 1L;

        /**
         * 企业应用id
         */
        @JsonProperty("agentid")
        private String agentId;

        /**
         * 企业应用是否打开地理位置上报 0：不上报；1：进入会话上报；2：持续上报
         */
        @JsonProperty("report_location_flag")
        private int rptLocalFlag;

        /**
         * 企业应用头像的mediaid，通过多媒体接口上传图片获得mediaid，上传后会自动裁剪成方形和圆形两个头像 
         */
        @JsonProperty("logo_mediaid")
        private String logoMediaId;

        /**
         * 企业应用名称
         */
        @JsonProperty("name")
        private String name;

        /**
         * 企业应用详情 
         */
        @JsonProperty("description")
        private String desc;

        /**
         * 企业应用可信域名
         */
        @JsonProperty("redirect_domain")
        private String rediDomain;

        /**
         * 是否接收用户变更通知。0：不接收；1：接收
         */
        @JsonProperty("isreportuser")
        private int isRptUser;

        /**
         * 是否上报用户进入应用事件。0：不接收；1：接收 
         */
        @JsonProperty("isreportenter")
        private int isRptEnter;

        public AgentAppBodyReq() {
            super();
        }

        public AgentAppBodyReq(String agentId) {
            this.agentId = agentId;
        }

        public AgentAppBodyReq(String agentId, int rptLocalFlag, String logoMediaId, String name, String desc, String rediDomain,
                int isRptUser, int isRptEnter) {
            super();
            this.agentId = agentId;
            this.rptLocalFlag = rptLocalFlag;
            this.logoMediaId = logoMediaId;
            this.name = name;
            this.desc = desc;
            this.rediDomain = rediDomain;
            this.isRptUser = isRptUser;
            this.isRptEnter = isRptEnter;
        }

        public String getAgentId() {
            return agentId;
        }

        public void setAgentId(String agentId) {
            this.agentId = agentId;
        }

        public int getRptLocalFlag() {
            return rptLocalFlag;
        }

        public void setRptLocalFlag(int rptLocalFlag) {
            this.rptLocalFlag = rptLocalFlag;
        }

        public String getLogoMediaId() {
            return logoMediaId;
        }

        public void setLogoMediaId(String logoMediaId) {
            this.logoMediaId = logoMediaId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getRediDomain() {
            return rediDomain;
        }

        public void setRediDomain(String rediDomain) {
            this.rediDomain = rediDomain;
        }

        public int getIsRptUser() {
            return isRptUser;
        }

        public void setIsRptUser(int isRptUser) {
            this.isRptUser = isRptUser;
        }

        public int getIsRptEnter() {
            return isRptEnter;
        }

        public void setIsRptEnter(int isRptEnter) {
            this.isRptEnter = isRptEnter;
        }

    }
}
