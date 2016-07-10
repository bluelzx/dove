package com.gustz.dove.cli.api.service.vo;

import com.gustz.dove.cli.api.service.vo.CommRsp.CommBodyRsp;

/**
 * TODO: Common response 
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 10, 2015 ]
 */
public class CommRsp extends AbstBaseRsp<CommBodyRsp> {

    private static final long serialVersionUID = 1L;

    public CommRsp(long sn, String websCode) {
        super(sn, websCode, null, new CommBodyRsp());
    }

    public static class CommBodyRsp extends ErrorBodyRsp {

        private static final long serialVersionUID = 1L;

    }
}
