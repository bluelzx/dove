package com.gustz.dove.cpcli.api.addrbook.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.gustz.dove.cpcli.api.service.conf.AddrbookWsUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.service.conf.WebsUrlParam;
import com.sinovatech.rd.wcsb.cli.api.service.impl.AbstBaseService;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.DepartmentReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.rsp.DeptListRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.rsp.DepartmentRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.service.DeptCpService;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.AcTokenCpService;

/**
 * 
 * TODO: 用户部门服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class DeptCpServiceImpl extends AbstBaseService<DepartmentReq> implements DeptCpService {

    @Autowired
    private AcTokenCpService acTokenCpService;

    /**
     * 创建部门
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public DepartmentRsp createDept(long sn, String cliAppCode, DepartmentReq req) {
        AddrbookWsUrl _wsUrl = AddrbookWsUrl.WSC01001CP;
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new DepartmentRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 更新部门
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public CommRsp updateDept(long sn, String cliAppCode, DepartmentReq req) {
        AddrbookWsUrl _wsUrl = AddrbookWsUrl.WSC01002CP;
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new CommRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 删除部门
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param id
     * @return
     */
    @Override
    public CommRsp deleteDept(long sn, String cliAppCode, String devAcCode, String id) {
        AddrbookWsUrl _wsUrl = AddrbookWsUrl.WSC01003CP;
        //
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        Map<String, String> _map = new HashMap<String, String>();
        _map.put(WebsUrlParam.DEPT_ID, id);
        this.setWebsUrlParamMap(_map);
        //
        return this.httpGet(_wsUrl, sn, cliAppCode, new CommRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 获取部门列表
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param id
     * @return
     */
    @Override
    public DeptListRsp listDept(long sn, String cliAppCode, String devAcCode, String id) {
        AddrbookWsUrl _wsUrl = AddrbookWsUrl.WSC01004CP;
        //
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        Map<String, String> _map = new HashMap<String, String>();
        _map.put(WebsUrlParam.DEPT_ID, id);
        this.setWebsUrlParamMap(_map);
        //
        return this.httpGet(_wsUrl, sn, cliAppCode, new DeptListRsp(sn, _wsUrl.getWebsCode()));
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenCpService.getAccessTokenCp(sn, cliAppCode, devAcCode));
    }

}
