package com.gustz.dove.cpcli.api.addrbook.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.gustz.dove.cpcli.api.service.conf.AddrbookWsUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.service.conf.WebsUrlParam;
import com.sinovatech.rd.wcsb.cli.api.service.impl.AbstBaseService;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.InviteUserReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.UserBaseReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.UserIdListReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.UserReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.rsp.InviteUserRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.rsp.UserListRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.rsp.UserRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.service.UserCpService;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.AcTokenCpService;

/**
 * 
 * TODO: 用户服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class UserCpServiceImpl extends AbstBaseService<AbstBaseReq<?>> implements UserCpService {

    @Autowired
    private AcTokenCpService acTokenCpService;

    /**
     * 创建用户
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public CommRsp createUser(long sn, String cliAppCode, UserReq req) {
        AddrbookWsUrl _wsUrl = AddrbookWsUrl.WSC00001CP;
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        // do 
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new CommRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 更新用户
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public CommRsp updateUser(long sn, String cliAppCode, UserReq req) {
        AddrbookWsUrl _wsUrl = AddrbookWsUrl.WSC00002CP;
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        // do 
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new CommRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 删除用户
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public CommRsp deleteUser(long sn, String cliAppCode, String devAcCode, String id) {
        AddrbookWsUrl _wsUrl = AddrbookWsUrl.WSC00003CP;
        //
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        Map<String, String> _map = new HashMap<String, String>();
        _map.put(WebsUrlParam.USER_ID, id);
        this.setWebsUrlParamMap(_map);
        // do 
        return this.httpGet(_wsUrl, sn, cliAppCode, new CommRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 批量删除用户
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public CommRsp batchDelUser(long sn, String cliAppCode, UserIdListReq req) {
        AddrbookWsUrl _wsUrl = AddrbookWsUrl.WSC00004CP;
        //
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        // do 
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new CommRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 查询用户
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param id
     * @return
     */
    @Override
    public UserRsp getUser(long sn, String cliAppCode, String devAcCode, String id) {
        AddrbookWsUrl _wsUrl = AddrbookWsUrl.WSC00005CP;
        // 请求参数
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        Map<String, String> _paramMap = new HashMap<String, String>();
        _paramMap.put(WebsUrlParam.USER_ID, id);
        this.setWebsUrlParamMap(_paramMap);
        // do 
        return this.httpGet(_wsUrl, sn, cliAppCode, new UserRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 查询部门下的用户
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public UserListRsp listByDept(long sn, String cliAppCode, UserBaseReq req) {
        //        
        return this.doListUser(AddrbookWsUrl.WSC00006CP, sn, cliAppCode, req);
    }

    /**
     * 查询部门下的用户详情
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public UserListRsp listInfoByDept(long sn, String cliAppCode, UserBaseReq req) {
        //
        return this.doListUser(AddrbookWsUrl.WSC00007CP, sn, cliAppCode, req);
    }

    private UserListRsp doListUser(AddrbookWsUrl wsUrl, long sn, String cliAppCode, UserBaseReq req) {
        // 请求参数
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        Map<String, String> _paramMap = new HashMap<String, String>();
        _paramMap.put(WebsUrlParam.DEPT_ID, req.getBody().getDeptId()); // 获取的部门id 
        //  1/0：是否递归获取子部门下面的成员
        _paramMap.put(WebsUrlParam.FETCH_CHILD, req.getBody().getFetchChild().toString());
        // 0获取全部成员，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。
        _paramMap.put(WebsUrlParam.STATE, req.getBody().getState().toString());
        this.setWebsUrlParamMap(_paramMap);
        // do 
        return this.httpGet(wsUrl, sn, cliAppCode, new UserListRsp(sn, wsUrl.getWebsCode()));
    }

    /**
     * 邀请成员关注
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public InviteUserRsp inviteUser(long sn, String cliAppCode, InviteUserReq req) {
        AddrbookWsUrl _wsUrl = AddrbookWsUrl.WSC00008CP;
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        // do 
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new InviteUserRsp(sn, _wsUrl.getWebsCode()));
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenCpService.getAccessTokenCp(sn, cliAppCode, devAcCode));
    }
}
