package com.gustz.dove.mpcli.api.user.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.gustz.dove.mpcli.api.service.base.MpCliTestBase;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserGroupReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserGroupReq.UserGroupBodyReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.rsp.UserGroupRsp;
import com.sinovatech.rd.wcsb.mpcli.api.user.service.UserGroupMpService;
import com.sinovatech.rd.wcsb.mpcli.api.user.vo.UserGroup;

/**
 * TODO: 用户群组服务接口实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 14, 2015 ]
 */
public class UserGroupMpServiceImplTest extends MpCliTestBase<String> {

    @Autowired
    private UserGroupMpService service;

    private UserGroupReq req;

    private String openId = "o-bc2v98_6iKrDS2ELiD04yd8B5k";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        UserGroup userGroup = new UserGroup(100, "test1");
        UserGroupBodyReq body = new UserGroupBodyReq(userGroup);
        body.setOpenId(openId);
        req = new UserGroupReq(devAcCode, body);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link UserGroupMpServiceImpl#createGroup(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.user.req.UserGroupReq)}.
     */
    @Test
    public void testCreateGroup() {
        UserGroupRsp rsp = service.createGroup(sn, cliAppCode, req);
        //
        System.out.println("createGroup-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

    /**
     * Test method for {@link UserGroupMpServiceImpl#listGroup(long, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testListGroup() {
        UserGroupRsp rsp = service.listGroup(sn, cliAppCode, devAcCode);
        //
        System.out.println("listGroup-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

    /**
     * Test method for {@link UserGroupMpServiceImpl#getGroupByOpenId(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.user.req.UserGroupReq)}.
     */
    @Test
    public void testGetGroupByOpenId() {
        UserGroupRsp rsp = service.getGroupByOpenId(sn, cliAppCode, req);
        //
        System.out.println("getGroupByOpenId-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

    /**
     * Test method for {@link UserGroupMpServiceImpl#updateGroup(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.user.req.UserGroupReq)}.
     */
    @Test
    public void testUpdateGroup() {
        UserGroup userGroup = new UserGroup(100, "测试组1");
        UserGroupBodyReq body = new UserGroupBodyReq(userGroup);
        body.setOpenId(openId);
        req = new UserGroupReq(devAcCode, body);
        //
        CommRsp rsp = service.updateGroup(sn, cliAppCode, req);
        //
        System.out.println("updateGroup-rsp=:" + rsp.getBody().getErrCode());
        Assert.assertNotNull(rsp.getBody().getErrCode());
    }

    /**
     * Test method for {@link UserGroupMpServiceImpl#updateGroupMembers(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.user.req.UserGroupReq)}.
     */
    @Test
    public void testUpdateGroupMembers() {
        UserGroupBodyReq body = new UserGroupBodyReq(null);
        body.setToGroupId(100 + "");
        body.setOpenId(openId);
        req = new UserGroupReq(devAcCode, body);
        //
        CommRsp rsp = service.updateGroupMembers(sn, cliAppCode, req);
        //
        System.out.println("updateGroupMembers-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

}
