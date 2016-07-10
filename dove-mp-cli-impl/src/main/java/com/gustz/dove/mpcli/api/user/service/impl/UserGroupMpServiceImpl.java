package com.gustz.dove.mpcli.api.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.service.impl.AbstBaseService;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.mpcli.api.security.service.AcTokenMpService;
import com.gustz.dove.mpcli.api.service.conf.UserWsUrl;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserGroupReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.rsp.UserGroupRsp;
import com.sinovatech.rd.wcsb.mpcli.api.user.service.UserGroupMpService;

/**
 * 
 * TODO: 用户群组服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class UserGroupMpServiceImpl extends AbstBaseService<UserGroupReq> implements UserGroupMpService {

    @Autowired
    private AcTokenMpService acTokenMpService;

    /**
     * 创建用户群组
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public UserGroupRsp createGroup(long sn, String cliAppCode, UserGroupReq req) {
        UserWsUrl _wsUrl = UserWsUrl.WSC01001MP;
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new UserGroupRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 查询全部用户群组
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @return
     */
    @Override
    public UserGroupRsp listGroup(long sn, String cliAppCode, String devAcCode) {
        UserWsUrl _wsUrl = UserWsUrl.WSC01002MP;
        this.setAccessTokenX(sn, cliAppCode, devAcCode);
        //
        return this.httpGet(_wsUrl, sn, cliAppCode, new UserGroupRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 查询用户所在分组
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public UserGroupRsp getGroupByOpenId(long sn, String cliAppCode, UserGroupReq req) {
        UserWsUrl _wsUrl = UserWsUrl.WSC01003MP;
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new UserGroupRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 更新用户群组
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public CommRsp updateGroup(long sn, String cliAppCode, UserGroupReq req) {
        UserWsUrl _wsUrl = UserWsUrl.WSC01004MP;
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new CommRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 更新群组的用户（移动用户）
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public CommRsp updateGroupMembers(long sn, String cliAppCode, UserGroupReq req) {
        UserWsUrl _wsUrl = UserWsUrl.WSC01005MP;
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new CommRsp(sn, _wsUrl.getWebsCode()));
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenMpService.getAccessTokenMp(sn, cliAppCode, devAcCode));
    }

}
