package com.gustz.dove.cpcli.api.addrbook.service;

import javax.jws.WebService;

import com.gustz.dove.cpcli.api.addrbook.req.InviteUserReq;
import com.gustz.dove.cpcli.api.addrbook.req.UserBaseReq;
import com.gustz.dove.cpcli.api.addrbook.req.UserIdListReq;
import com.gustz.dove.cpcli.api.addrbook.req.UserReq;
import com.gustz.dove.cpcli.api.addrbook.rsp.InviteUserRsp;
import com.gustz.dove.cpcli.api.addrbook.rsp.UserListRsp;
import com.gustz.dove.cpcli.api.addrbook.rsp.UserRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;

/**
 * 
 * TODO: 用户服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 7, 2015 ]
 */
@WebService
public interface UserCpService {

    /**
     * 创建用户
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    CommRsp createUser(long sn, String cliAppCode, UserReq req);

    /**
     * 更新用户
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    CommRsp updateUser(long sn, String cliAppCode, UserReq req);

    /**
     * 删除用户
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param id
     * @return
     */
    CommRsp deleteUser(long sn, String cliAppCode, String devAcCode, String id);

    /**
     * 批量删除用户
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    CommRsp batchDelUser(long sn, String cliAppCode, UserIdListReq req);

    /**
     * 查询用户
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param id
     * @return
     */
    UserRsp getUser(long sn, String cliAppCode, String devAcCode, String id);

    /**
     * 查询部门下的用户
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    UserListRsp listByDept(long sn, String cliAppCode, UserBaseReq req);

    /**
     * 查询部门下的用户详情
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    UserListRsp listInfoByDept(long sn, String cliAppCode, UserBaseReq req);

    /**
     * 邀请成员关注
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    InviteUserRsp inviteUser(long sn, String cliAppCode, InviteUserReq req);

}