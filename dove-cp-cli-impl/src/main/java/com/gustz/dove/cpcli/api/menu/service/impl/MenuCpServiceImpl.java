package com.gustz.dove.cpcli.api.menu.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.menu.req.MenuReq;
import com.sinovatech.rd.wcsb.cli.api.menu.rsp.MenuRsp;
import com.sinovatech.rd.wcsb.cli.api.menu.service.impl.MenuServiceImpl;
import com.sinovatech.rd.wcsb.cli.api.service.conf.WebsUrlParam;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.cpcli.api.menu.service.MenuCpService;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.AcTokenCpService;
import com.gustz.dove.cpcli.api.service.conf.MenuWsUrl;
import com.sinovatech.rd.wcsb.repo.account.AccConstants;

/**
 * 
 * TODO: 菜单服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class MenuCpServiceImpl extends MenuServiceImpl implements MenuCpService {

    @Autowired
    private AcTokenCpService acTokenCpService;

    /**
     * 创建菜单
     * 
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param req 菜单请求VO 
     * @return
     */
    @Override
    public CommRsp createMenu(long sn, String cliAppCode, MenuReq req) {
        Map<String, String> _paramMap = new HashMap<String, String>();
        _paramMap.put(WebsUrlParam.AGENT_ID, AccConstants.getCpSrcId(req.getDevAcCode()));
        this.setWebsUrlParamMap(_paramMap);
        //
        return this.createMenu(MenuWsUrl.WSC05001CP, sn, cliAppCode, req);
    }

    /**
     * 删除菜单
     * 
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param devAcCode
     * @return
     */
    @Override
    public CommRsp delMenu(long sn, String cliAppCode, String devAcCode) {
        Map<String, String> _paramMap = new HashMap<String, String>();
        _paramMap.put(WebsUrlParam.AGENT_ID, AccConstants.getCpSrcId(devAcCode));
        this.setWebsUrlParamMap(_paramMap);
        //
        return this.delMenu(MenuWsUrl.WSC05003CP, sn, cliAppCode, devAcCode);
    }

    /**
     * 查询菜单
     * 
     * @param sn 序号
     * @param cliAppCode 客户端AppCode
     * @param devAcCode
     * @return
     */
    @Override
    public MenuRsp getMenu(long sn, String cliAppCode, String devAcCode) {
        Map<String, String> _paramMap = new HashMap<String, String>();
        _paramMap.put(WebsUrlParam.AGENT_ID, AccConstants.getCpSrcId(devAcCode));
        this.setWebsUrlParamMap(_paramMap);
        //
        return this.getMenu(MenuWsUrl.WSC05002CP, sn, cliAppCode, devAcCode);
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenCpService.getAccessTokenCp(sn, cliAppCode, devAcCode));
    }

}
