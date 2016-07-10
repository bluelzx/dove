package com.gustz.dove.cpcli.api.agent.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.cpcli.api.agent.rsp.AgentAppListRsp.AgentAppListBodyRsp;

/**
 * 
 * TODO: 代理应用集响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class AgentAppListRsp extends AbstBaseRsp<AgentAppListBodyRsp> {

    private static final long serialVersionUID = 1L;

    public AgentAppListRsp(long sn, String websCode) {
        super(sn, websCode, null, new AgentAppListBodyRsp());
    }

    public static class AgentAppListBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        /**
         * 企业应用集
         */
        @JsonProperty("agentlist")
        private AgentAppList[] agentList;

        public AgentAppListBodyRsp() {
            super();
        }

        public AgentAppListBodyRsp(AgentAppList[] agentList) {
            super();
            this.agentList = agentList;
        }

        public AgentAppList[] getAgentList() {
            return agentList;
        }

        public void setAgentList(AgentAppList[] agentList) {
            this.agentList = agentList;
        }

        public static class AgentAppList extends AbstCliBaseVo {

            private static final long serialVersionUID = 1L;

            /**
             * 企业应用id
             */
            @JsonProperty("agentid")
            private String agentId;

            /**
             * 企业应用名称
             */
            @JsonProperty("name")
            private String name;

            /**
             * 企业应用方形头像
             */
            @JsonProperty("square_logo_url")
            private String squareLogoUrl;

            /**
             * 企业应用圆形头像
             */
            @JsonProperty("round_logo_url")
            private String roundLogoUrl;

            public AgentAppList() {
                super();
            }

            public AgentAppList(String agentId, String name, String squareLogoUrl, String roundLogoUrl) {
                super();
                this.agentId = agentId;
                this.name = name;
                this.squareLogoUrl = squareLogoUrl;
                this.roundLogoUrl = roundLogoUrl;
            }

            public String getAgentId() {
                return agentId;
            }

            public void setAgentId(String agentId) {
                this.agentId = agentId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSquareLogoUrl() {
                return squareLogoUrl;
            }

            public void setSquareLogoUrl(String squareLogoUrl) {
                this.squareLogoUrl = squareLogoUrl;
            }

            public String getRoundLogoUrl() {
                return roundLogoUrl;
            }

            public void setRoundLogoUrl(String roundLogoUrl) {
                this.roundLogoUrl = roundLogoUrl;
            }
        }
    }

}