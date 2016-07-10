package com.gustz.dove.cpcli.api.agent.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.service.conf.WebsUrlParam;
import com.sinovatech.rd.wcsb.cli.api.service.impl.AbstBaseService;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.cpcli.api.agent.req.AgentAppReq;
import com.sinovatech.rd.wcsb.cpcli.api.agent.rsp.AgentAppListRsp;
import com.sinovatech.rd.wcsb.cpcli.api.agent.rsp.AgentAppRsp;
import com.sinovatech.rd.wcsb.cpcli.api.agent.service.AgentCpService;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.AcTokenCpService;
import com.gustz.dove.cpcli.api.service.conf.AgentWsUrl;
import com.sinovatech.rd.wcsb.repo.account.AccConstants;

/**
 * 
 * TODO: 代理商服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class AgentCpServiceImpl extends AbstBaseService<AgentAppReq> implements AgentCpService {

    @Autowired
    private AcTokenCpService acTokenCpService;

    /**
     * 获取企业号应用
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    @Override
    public AgentAppRsp getAgentApp(long sn, String cliAppCode, String devAcCode) {
        AgentWsUrl _wsUrl = AgentWsUrl.WSC02001CP;
        //
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        Map<String, String> _map = new HashMap<String, String>();
        _map.put(WebsUrlParam.AGENT_ID, AccConstants.getCpSrcId(devAcCode));
        this.setWebsUrlParamMap(_map);
        //
        return this.httpGet(_wsUrl, sn, cliAppCode, new AgentAppRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 设置企业号应用
     * 
     * @param sn
     * @param cliAppCode 
     * @param req
     * @return
     */
    @Override
    public CommRsp setAgentApp(long sn, String cliAppCode, AgentAppReq req) {
        AgentWsUrl _wsUrl = AgentWsUrl.WSC02002CP;
        //
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new CommRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 获取应用概况列表
     * 
     * @param sn
     * @param cliAppCode 
     * @param devAcCode
     * @return
     */
    @Override
    public AgentAppListRsp listAgentApp(long sn, String cliAppCode, String devAcCode) {
        AgentWsUrl _wsUrl = AgentWsUrl.WSC02003CP;
        //
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        //
        return this.httpGet(_wsUrl, sn, cliAppCode, new AgentAppListRsp(sn, _wsUrl.getWebsCode()));
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenCpService.getAccessTokenCp(sn, cliAppCode, devAcCode));
    }

}
