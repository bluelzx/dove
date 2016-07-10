package com.gustz.dove.cpcli.api.addrbook.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.DepartmentReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.DepartmentReq.UserDeptBodyReq;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.rsp.DepartmentRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.rsp.DeptListRsp;
import com.sinovatech.rd.wcsb.cpcli.api.addrbook.service.DeptCpService;
import com.gustz.dove.cpcli.api.service.base.CpCliTestBase;

/**
 * TODO: 用户部门服务接口实现的测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 14, 2015 ]
 */
public class DeptCpServiceImplTest extends CpCliTestBase<String> {

    @Autowired
    private DeptCpService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link DeptCpServiceImpl#createGroup(long, java.lang.String, com.sinovatech.rd.wcsb.cpcli.api.addrbook.req.DepartmentReq)}.
     */
    @Test
    public void testCreateDept() {
        DepartmentReq req = new DepartmentReq(devAcCode, new UserDeptBodyReq(null, "产品开发部3", 1, 3));
        //
        DepartmentRsp rsp = service.createDept(sn, cliAppCode, req);
        //
        System.out.println("createDept-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testUpdateDept() {
        DepartmentReq req = new DepartmentReq(devAcCode, new UserDeptBodyReq(5, "产品开发部3X", 1, 3));
        //
        CommRsp rsp = service.updateDept(sn, cliAppCode, req);
        //
        System.out.println("updateDept-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testDeleteDept() {
        CommRsp rsp = service.deleteDept(sn, cliAppCode, devAcCode, "5");
        //
        System.out.println("deleteDept-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    @Test
    public void testListDept() {
        DeptListRsp rsp = service.listDept(sn, cliAppCode, devAcCode, null);
        //
        System.out.println("listDept-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

}
