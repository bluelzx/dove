package com.gustz.dove.cpcli.api.agent.service.impl;

import com.gustz.dove.cpcli.api.service.base.CpCliTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.cpcli.api.agent.req.AgentAppReq;
import com.sinovatech.rd.wcsb.cpcli.api.agent.req.AgentAppReq.AgentAppBodyReq;
import com.sinovatech.rd.wcsb.cpcli.api.agent.rsp.AgentAppListRsp;
import com.sinovatech.rd.wcsb.cpcli.api.agent.rsp.AgentAppRsp;
import com.sinovatech.rd.wcsb.cpcli.api.agent.service.AgentCpService;

/**
 * TODO: 代理商服务的接口实现测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 13, 2015 ]
 */
public class AgentCpServiceImplTest extends CpCliTestBase<String> {

    @Autowired
    private AgentCpService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testGetAgentApp() {
        AgentAppRsp rsp = service.getAgentApp(sn, cliAppCode, devAcCode);
        //
        System.out.println("getAgentApp-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testSetAgentApp() {
        AgentAppBodyReq body = new AgentAppBodyReq();
        body.setAgentId(devAcCode);
        body.setDesc("销售日报系统描述");
        body.setIsRptEnter(1);
        body.setIsRptUser(1);
        body.setRptLocalFlag(1);
        body.setName("销售日报系统");
        AgentAppReq req = new AgentAppReq(devAcCode, body);
        //
        CommRsp rsp = service.setAgentApp(sn, cliAppCode, req);
        //
        System.out.println("setAgentApp-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testListAgentApp() {
        AgentAppListRsp rsp = service.listAgentApp(sn, cliAppCode, devAcCode);
        //
        System.out.println("listAgentApp-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

}
