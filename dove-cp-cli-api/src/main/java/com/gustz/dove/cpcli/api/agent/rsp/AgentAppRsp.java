package com.gustz.dove.cpcli.api.agent.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstCliBaseVo;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.cpcli.api.agent.rsp.AgentAppRsp.AgentAppBodyRsp;

/**
 * 
 * TODO: 代理应用响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class AgentAppRsp extends AbstBaseRsp<AgentAppBodyRsp> {

    private static final long serialVersionUID = 1L;

    public AgentAppRsp(long sn, String websCode) {
        super(sn, websCode, null, new AgentAppBodyRsp());
    }

    public static class AgentAppBodyRsp extends ErrorBodyRsp {

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
         * 企业应用是否被禁用
         */
        @JsonProperty("close")
        private int close;

        /**
         * 企业应用是否打开地理位置上报 0：不上报；1：进入会话上报；2：持续上报
         */
        @JsonProperty("report_location_flag")
        private int rptLocalFlag;

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

        /**
         * 企业应用可见范围（部门）
         */
        @JsonProperty("allow_partys")
        private AllowParty allowParty;

        /**
         *  企业应用可见范围（标签） 
         */
        @JsonProperty("allow_tags")
        private AllowTag allowTag;

        /**
         * 企业应用可见范围（人员），其中包括userid和关注状态state 
         */
        @JsonProperty("allow_userinfos")
        private AllowUserInfo allowUserInfo;

        public AgentAppBodyRsp() {
            super();
        }

        public AgentAppBodyRsp(String agentId, String name, String squareLogoUrl, String roundLogoUrl, String desc,
                String rediDomain, int close, int rptLocalFlag, int isRptUser, int isRptEnter, AllowParty allowParty,
                AllowTag allowTag, AllowUserInfo allowUserInfo) {
            super();
            this.agentId = agentId;
            this.name = name;
            this.squareLogoUrl = squareLogoUrl;
            this.roundLogoUrl = roundLogoUrl;
            this.desc = desc;
            this.rediDomain = rediDomain;
            this.close = close;
            this.rptLocalFlag = rptLocalFlag;
            this.isRptUser = isRptUser;
            this.isRptEnter = isRptEnter;
            this.allowParty = allowParty;
            this.allowTag = allowTag;
            this.allowUserInfo = allowUserInfo;
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

        public int getClose() {
            return close;
        }

        public void setClose(int close) {
            this.close = close;
        }

        public int getRptLocalFlag() {
            return rptLocalFlag;
        }

        public void setRptLocalFlag(int rptLocalFlag) {
            this.rptLocalFlag = rptLocalFlag;
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

        public AllowParty getAllowParty() {
            return allowParty;
        }

        public void setAllowParty(AllowParty allowParty) {
            this.allowParty = allowParty;
        }

        public AllowTag getAllowTag() {
            return allowTag;
        }

        public void setAllowTag(AllowTag allowTag) {
            this.allowTag = allowTag;
        }

        public AllowUserInfo getAllowUserInfo() {
            return allowUserInfo;
        }

        public void setAllowUserInfo(AllowUserInfo allowUserInfo) {
            this.allowUserInfo = allowUserInfo;
        }

        public static class AllowParty extends AbstCliBaseVo {

            private static final long serialVersionUID = 1L;

            /**
             * 部门ID集
             */
            @JsonProperty("partyid")
            private int[] deptIds;

            public AllowParty() {
                super();
            }

            public AllowParty(int[] deptIds) {
                super();
                this.deptIds = deptIds;
            }

            public int[] getDeptIds() {
                return deptIds;
            }

            public void setDeptIds(int[] deptIds) {
                this.deptIds = deptIds;
            }

        }

        public static class AllowTag extends AbstCliBaseVo {

            private static final long serialVersionUID = 1L;

            /**
             * 标签ID集
             */
            @JsonProperty("tagid")
            private int[] tagIds;

            public AllowTag() {
                super();
            }

            public AllowTag(int[] tagIds) {
                super();
                this.tagIds = tagIds;
            }

            public int[] getTagIds() {
                return tagIds;
            }

            public void setTagIds(int[] tagIds) {
                this.tagIds = tagIds;
            }

        }

        public static class AllowUserInfo extends AbstCliBaseVo {

            private static final long serialVersionUID = 1L;

            /**
             * 用户简单信息
             */
            @JsonProperty("user")
            private SimpleUser[] simpleUser;

            public AllowUserInfo() {
                super();
            }

            public AllowUserInfo(SimpleUser[] simpleUser) {
                super();
                this.simpleUser = simpleUser;
            }

            public SimpleUser[] getSimpleUser() {
                return simpleUser;
            }

            public void setSimpleUser(SimpleUser[] simpleUser) {
                this.simpleUser = simpleUser;
            }

            public static class SimpleUser extends AbstCliBaseVo {

                private static final long serialVersionUID = 1L;

                /**
                 * 用户ID
                 */
                @JsonProperty("userid")
                private String userId;

                /**
                 * 状态
                 */
                @JsonProperty("status")
                private String status;

                public SimpleUser() {
                    super();
                }

                public SimpleUser(String userId, String status) {
                    super();
                    this.userId = userId;
                    this.status = status;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

            }

        }

    }

}