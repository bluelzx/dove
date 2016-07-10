package com.gustz.dove.cpcli.api.agent.service;

import javax.jws.WebService;

import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.gustz.dove.cpcli.api.agent.req.AgentAppReq;
import com.gustz.dove.cpcli.api.agent.rsp.AgentAppListRsp;
import com.gustz.dove.cpcli.api.agent.rsp.AgentAppRsp;

/**
 * 
 * TODO: 代理商服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 7, 2015 ]
 */
@WebService
public interface AgentCpService {

    /**
     * 获取企业号应用
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    AgentAppRsp getAgentApp(long sn, String cliAppCode, String devAcCode);

    /**
     * 设置企业号应用
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    CommRsp setAgentApp(long sn, String cliAppCode, AgentAppReq req);

    /**
     * 获取应用概况列表
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    AgentAppListRsp listAgentApp(long sn, String cliAppCode, String devAcCode);

}