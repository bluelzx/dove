package com.gustz.dove.mpcli.api.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gustz.dove.mpcli.api.service.base.MpCliTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.service.dict.LangTypeDict;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserListReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserListReq.UserListBodyReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserListReq.UserListBodyReq.SimpleUser;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.req.UserReq.UserBodyReq;
import com.sinovatech.rd.wcsb.mpcli.api.user.rsp.UserListRsp;
import com.sinovatech.rd.wcsb.mpcli.api.user.rsp.UserOpenIdRsp;
import com.sinovatech.rd.wcsb.mpcli.api.user.rsp.UserRsp;
import com.sinovatech.rd.wcsb.mpcli.api.user.service.UserMpService;

/**
 * TODO: 用户服务接口实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 14, 2015 ]
 */
public class UserMpServiceImplTest extends MpCliTestBase<String> {

    @Autowired
    private UserMpService service;

    private UserReq req;

    private String openId = "o-bc2v98_6iKrDS2ELiD04yd8B5k";

    private String openId2 = "o-bc2v2Db7zDsSjC4sZ7DbDYli0A";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        UserBodyReq body = new UserBodyReq(openId);
        req = new UserReq(devAcCode, body);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link UserMpServiceImpl#getUser(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.user.req.UserReq)}.
     */
    @Test
    public void testGetUser() {
        //
        UserRsp rsp = service.getUser(sn, cliAppCode, req);
        System.out.println("getUser-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

    /**
     * Test method for {@link UserMpServiceImpl#listUserOpenId(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.user.req.UserReq)}.
     */
    @Test
    public void testListUserOpenId() {
        //
        UserOpenIdRsp rsp = service.listUserOpenId(sn, cliAppCode, req);
        System.out.println("listUserOpenId-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

    /**
     * Test method for {@link UserMpServiceImpl#listUser(long, java.lang.String, com.sinovatech.rd.wcsb.mpcli.api.user.req.UserListReq)}.
     */
    @Test
    public void testListUser() {
        List<SimpleUser> userList = new ArrayList<SimpleUser>();
        userList.add(new SimpleUser(openId, LangTypeDict.CHINA));
        userList.add(new SimpleUser(openId2, LangTypeDict.CHINA));
        //
        UserListBodyReq body = new UserListBodyReq(userList);
        UserListReq _listReq = new UserListReq(devAcCode, body);
        //
        UserListRsp rsp = service.listUser(sn, cliAppCode, _listReq);
        System.out.println("listUser-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

}
