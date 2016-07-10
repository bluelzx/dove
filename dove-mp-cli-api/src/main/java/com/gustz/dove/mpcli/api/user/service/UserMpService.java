package com.gustz.dove.mpcli.api.user.service;

import javax.jws.WebService;

import com.gustz.dove.mpcli.api.user.req.UserListReq;
import com.gustz.dove.mpcli.api.user.rsp.UserListRsp;
import com.gustz.dove.mpcli.api.user.rsp.UserOpenIdRsp;
import com.gustz.dove.mpcli.api.user.rsp.UserRsp;
import com.gustz.dove.mpcli.api.user.req.UserReq;

/**
 * 
 * TODO: 用户服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 7, 2015 ]
 */
@WebService
public interface UserMpService {

    /**
     * 获取用户详情
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    UserRsp getUser(long sn, String cliAppCode, UserReq req);

    /**
     * 获取关注者OpenId列表
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    UserOpenIdRsp listUserOpenId(long sn, String cliAppCode, UserReq req);

    /**
     * 批量获取关注者用户基本信息
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    UserListRsp listUser(long sn, String cliAppCode, UserListReq req);

}