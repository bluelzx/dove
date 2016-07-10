package com.gustz.dove.cpcli.api.addrbook.service;

import javax.jws.WebService;

import com.gustz.dove.cpcli.api.addrbook.req.DepartmentReq;
import com.gustz.dove.cpcli.api.addrbook.rsp.DepartmentRsp;
import com.gustz.dove.cpcli.api.addrbook.rsp.DeptListRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.CommRsp;

/**
 * 
 * TODO: 部门服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 7, 2015 ]
 */
@WebService
public interface DeptCpService {

    /**
     * 创建部门
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    DepartmentRsp createDept(long sn, String cliAppCode, DepartmentReq req);

    /**
     * 更新部门
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    CommRsp updateDept(long sn, String cliAppCode, DepartmentReq req);

    /**
     * 删除部门
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param id
     * @return
     */
    CommRsp deleteDept(long sn, String cliAppCode, String devAcCode, String id);

    /**
     * 获取部门列表
     * 
     * @param sn
     * @param cliAppCode
     * @param devAcCode
     * @param id
     * @return
     */
    DeptListRsp listDept(long sn, String cliAppCode, String devAcCode, String id);

}