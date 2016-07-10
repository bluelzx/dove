package com.gustz.dove.cpcli.api.addrbook.req;

import com.gustz.dove.cpcli.api.addrbook.vo.CpUser;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;

/**
 * 
 * TODO: 用户基础请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 6, 2015 ]
 */
public class UserReq extends AbstBaseReq<CpUser> {

    private static final long serialVersionUID = 1L;

    public UserReq(String devAcCode, CpUser body) {
        super(devAcCode, body);
    }

}
