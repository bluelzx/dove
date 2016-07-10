package com.gustz.dove.mpcli.api.user.service;

import javax.jws.WebService;

import com.gustz.dove.mpcli.api.user.req.UserGroupReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.gustz.dove.mpcli.api.user.rsp.UserGroupRsp;

/**
 * 
 * TODO: 用户群组服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 7, 2015 ]
 */
@WebService
public interface UserGroupMpService {

    /**
     * 创建用户群组
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    UserGroupRsp createGroup(long sn, String cliAppCode, UserGroupReq req);

    /**
     * 查询全部用户群组
     * 
     * @param sn
     * @param cliAppCode
     * @param acCode
     * @return
     */
    UserGroupRsp listGroup(long sn, String cliAppCode, String acCode);

    /**
     * 查询用户所在分组
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    UserGroupRsp getGroupByOpenId(long sn, String cliAppCode, UserGroupReq req);

    /**
     * 更新用户群组
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    CommRsp updateGroup(long sn, String cliAppCode, UserGroupReq req);

    /**
     * 更新群组的用户（移动用户）
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    CommRsp updateGroupMembers(long sn, String cliAppCode, UserGroupReq req);

}