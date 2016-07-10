package com.gustz.dove.mpcli.api.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.service.conf.WebsUrlParam;
import com.sinovatech.rd.wcsb.cli.api.service.dict.LangTypeDict;
import com.sinovatech.rd.wcsb.cli.api.service.impl.AbstBaseService;
import com.sinovatech.rd.wcsb.mpcli.api.security.service.AcTokenMpService;
import com.gustz.dove.mpcli.api.service.conf.UserWsUrl;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserListReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.rsp.UserListRsp;
import com.sinovatech.rd.wcsb.mpcli.api.user.rsp.UserOpenIdRsp;
import com.sinovatech.rd.wcsb.mpcli.api.user.rsp.UserRsp;
import com.sinovatech.rd.wcsb.mpcli.api.user.service.UserMpService;

/**
 * 
 * TODO: 用户服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class UserMpServiceImpl extends AbstBaseService<UserListReq> implements UserMpService {

    @Autowired
    private AcTokenMpService acTokenMpService;

    /**
     * 获取用户详情
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public UserRsp getUser(long sn, String cliAppCode, UserReq req) {
        UserWsUrl _wsUrl = UserWsUrl.WSC00001MP;
        // 请求参数
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        Map<String, String> _paramMap = new HashMap<String, String>();
        _paramMap.put(WebsUrlParam.OPEN_ID, req.getBody().getOpenId()); // 公众号
        _paramMap.put(WebsUrlParam.LANG, LangTypeDict.CHINA.getName()); // 语言
        this.setWebsUrlParamMap(_paramMap);
        // do 
        return this.httpGet(_wsUrl, sn, cliAppCode, new UserRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 获取关注者OpenId列表
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public UserOpenIdRsp listUserOpenId(long sn, String cliAppCode, UserReq req) {
        UserWsUrl _wsUrl = UserWsUrl.WSC00002MP;
        // 请求参数
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        Map<String, String> _paramMap = new HashMap<String, String>();
        _paramMap.put(WebsUrlParam.NEXT_OPEN_ID, req.getBody().getOpenId()); // 下一个公众号
        this.setWebsUrlParamMap(_paramMap);
        //
        return this.httpGet(_wsUrl, sn, cliAppCode, new UserOpenIdRsp(sn, _wsUrl.getWebsCode()));
    }

    /**
     * 批量获取关注者用户基本信息
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public UserListRsp listUser(long sn, String cliAppCode, UserListReq req) {
        UserWsUrl _wsUrl = UserWsUrl.WSC00003MP;
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        //
        return this.httpPost(_wsUrl, sn, cliAppCode, req, new UserListRsp(sn, _wsUrl.getWebsCode()));
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenMpService.getAccessTokenMp(sn, cliAppCode, devAcCode));
    }

}
