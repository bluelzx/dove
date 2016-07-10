package com.gustz.dove.cpcli.api.addrbook.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.service.dict.GenderDict;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.InviteUserReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.InviteUserReq.InviteUserBodyReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.UserBaseReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.UserBaseReq.UserBaseBodyReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.UserIdListReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.UserIdListReq.UserIdListBodyReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.UserReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.rsp.InviteUserRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.rsp.UserListRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.rsp.UserRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.service.UserCpService;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.vo.CpUser;
import com.gustz.dove.cpcli.api.service.base.CpCliTestBase;

/**
 * TODO: 用户服务接口实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 14, 2015 ]
 */
public class UserCpServiceImplTest extends CpCliTestBase<String> {

    @Autowired
    private UserCpService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreateUser() {
        CpUser body = new CpUser();
        body.setUserId("userid3");
        body.setName("name3");
        body.setDeptIds(new int[] { 2 });
        body.setGender(GenderDict.MAN);
        body.setMobile("13112345678");
        body.setEmail("123@163.com");
        body.setWeixinId("tlx-123456");
        //
        UserReq req = new UserReq(devAcCode, body);
        //
        CommRsp rsp = service.createUser(sn, cliAppCode, req);
        //
        System.out.println("createUser-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testUpdateUser() {
        CpUser body = new CpUser();
        body.setUserId("userid2");
        body.setName("name2X");
        body.setDeptIds(new int[] { 2 });
        body.setGender(GenderDict.MAN);
        //
        UserReq req = new UserReq(devAcCode, body);
        //
        CommRsp rsp = service.updateUser(sn, cliAppCode, req);
        //
        System.out.println("updateUser-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testDeleteUser() {
        //
        CommRsp rsp = service.deleteUser(sn, cliAppCode, devAcCode, "userid2");
        //
        System.out.println("deleteUser-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testBatchDelUser() {
        UserIdListReq req = new UserIdListReq(devAcCode, new UserIdListBodyReq(new String[] { "userid2" }));
        //
        CommRsp rsp = service.batchDelUser(sn, cliAppCode, req);
        //
        System.out.println("batchDelUser-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testGetUser() {
        UserRsp rsp = service.getUser(sn, cliAppCode, devAcCode, "userid2");
        //
        System.out.println("getUser-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testListByDept() {
        UserBaseBodyReq body = new UserBaseBodyReq();
        body.setDeptId("2");
        //body.setFetchChild(YnDict.Y);
        //body.setState(UserStatusDict.ALL);
        UserBaseReq req = new UserBaseReq(devAcCode, body);
        //
        UserListRsp rsp = service.listByDept(sn, cliAppCode, req);
        //
        System.out.println("listByDept-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testListInfoByDept() {
        UserBaseBodyReq body = new UserBaseBodyReq();
        body.setDeptId("2");
        //body.setFetchChild(YnDict.Y);
        //body.setState(UserStatusDict.ALL);
        UserBaseReq req = new UserBaseReq(devAcCode, body);
        //
        UserListRsp rsp = service.listInfoByDept(sn, cliAppCode, req);
        //
        System.out.println("listInfoByDept-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testInviteUser() {
        InviteUserReq req = new InviteUserReq(devAcCode, new InviteUserBodyReq("dev1"));
        //
        InviteUserRsp rsp = service.inviteUser(sn, cliAppCode, req);
        //
        System.out.println("inviteUser-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

}
