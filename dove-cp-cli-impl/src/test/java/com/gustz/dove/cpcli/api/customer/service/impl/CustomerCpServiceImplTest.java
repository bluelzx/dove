package com.gustz.dove.cpcli.api.customer.service.impl;

import com.gustz.dove.cpcli.api.service.base.CpCliTestBase;
import net.sf.ehcache.CacheManager;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.sinovatech.rd.wcsb.cli.api.customer.vo.TextCust;
import com.sinovatech.rd.wcsb.cli.api.service.dict.YnDict;
import com.sinovatech.rd.wcsb.cpcli.api.customer.req.TextCustReq;
import com.sinovatech.rd.wcsb.cpcli.api.customer.req.TextCustReq.TextBodyCustReq;
import com.sinovatech.rd.wcsb.cpcli.api.customer.rsp.CommCustRsp;
import com.sinovatech.rd.wcsb.cpcli.api.customer.service.CustomerCpService;

/**
 * TODO: 客服接口实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 7, 2015 ]
 */
public class CustomerCpServiceImplTest extends CpCliTestBase<String> {

    @Autowired
    private CustomerCpService service;

    @SuppressWarnings("unused")
    private String openId = "o-bc2v2Db7zDsSjC4sZ7DbDYli0A";

    @Autowired
    private EhCacheCacheManager cacheManager;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link CustomerCpServiceImpl#sendCustomerMsg(long, java.lang.String, com.sinovatech.rd.wcsb.cpcli.api.customer.req.CustBaseReq)}.
     * @throws Exception 
     */
    @Test
    public void testSendCustomerMsg() throws Exception {
        String text = "<a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcbf844f69e6cff87&redirect_uri=http://124.192.56.203/dayrpt-web/wcsbus/doOauth.do&response_type=code&scope=snsapi_base&state=state123#wechat_redirect'>OAuth2测试</a>";
        TextBodyCustReq body = new TextBodyCustReq(new TextCust(text + System.currentTimeMillis()));
        body.setAgentId(agentId);
        body.setSafe(YnDict.N);
        body.setToUserExt(new String[] { "ZZF", "dev1", "dev2" });
        //body.setToDeptExt(new String[] { "2" });
        body.setToTagExt(new String[] { "3" });
        //body.setToDept(TextBodyCustReq.TO_ALL_USER);
        //body.setToTag(TextBodyCustReq.TO_ALL_USER);
        //body.setToUser(TextBodyCustReq.TO_ALL_USER);
        TextCustReq req = new TextCustReq(devAcCode, body);
        req.setCliAppPwd(cliAppPwd);
        req.setDevAcCode(devAcCode);
        req.setCliIpAddrs(cliIpAddrs);
        // do
        CommCustRsp rsp = service.sendCustomerMsg(sn, cliAppCode, req);
        //
        System.out.println("service.sendCustomerMsg-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
        //
        // testEhcache();
    }

    @Test
    public void testEhcache() {
        CacheManager mgr = cacheManager.getCacheManager();
        net.sf.ehcache.Cache cache = mgr.getCache("ac_token_cache");
        System.out.println(cache.get("test-client3_2"));
        System.out.println(cache.getKeys());
    }

}
