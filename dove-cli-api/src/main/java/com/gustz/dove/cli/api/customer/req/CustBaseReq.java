package com.gustz.dove.cli.api.customer.req;

import com.gustz.dove.cli.api.service.vo.AbstBaseReq;

/**
 * 
 * TODO: 客服请求报文基类
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class CustBaseReq<T> extends AbstBaseReq<T> {

    private static final long serialVersionUID = 1L;

    protected CustBaseReq(String devAcCode, T body) {
        super(devAcCode, body);
    }
}
