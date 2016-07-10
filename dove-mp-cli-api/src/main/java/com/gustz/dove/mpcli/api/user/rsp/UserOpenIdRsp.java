package com.gustz.dove.mpcli.api.user.rsp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.gustz.dove.mpcli.api.user.rsp.UserOpenIdRsp.UserOpenIdBodyRsp;

/**
 * 
 * TODO: 用户公众号响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class UserOpenIdRsp extends AbstBaseRsp<UserOpenIdBodyRsp> {

    private static final long serialVersionUID = 1L;

    public UserOpenIdRsp(long sn, String websCode) {
        super(sn, websCode, null, new UserOpenIdBodyRsp());
    }

    public static class UserOpenIdBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

        // 关注该公众账号的总用户数
        @JsonProperty("total")
        private int total;

        // 拉取的OPENID个数，最大值为10000 
        @JsonProperty("count")
        private int count;

        // 列表数据，OPENID的列表 
        @JsonProperty("data")
        private OpenIdData data;

        // 拉取列表的最后一个用户的OPENID
        @JsonProperty("next_openid")
        private String nextOpenId;

        public UserOpenIdBodyRsp() {
            super();
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public OpenIdData getData() {
            return data;
        }

        public void setData(OpenIdData data) {
            this.data = data;
        }

        public String getNextOpenId() {
            return nextOpenId;
        }

        public void setNextOpenId(String nextOpenId) {
            this.nextOpenId = nextOpenId;
        }

        public static class OpenIdData {

            // 公众号集
            @JsonProperty("openid")
            private String[] openIds;

            public String[] getOpenIds() {
                return openIds;
            }

            public void setOpenIds(String[] openIds) {
                this.openIds = openIds;
            }
        }

    }

}
