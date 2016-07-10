package com.gustz.dove.cli.api.security.rsp;

import com.gustz.dove.cli.api.security.vo.AccessToken;
import com.gustz.dove.cli.api.service.vo.AbstBaseRsp;

/**
 * 
 * TODO: 接口凭证响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class AccessTokenRsp extends AbstBaseRsp<AccessToken> {

    private static final long serialVersionUID = 1L;

    public AccessTokenRsp(long sn, String websCode, String rspCode, AccessToken body) {
        super(sn, websCode, rspCode, body);
    }

    public AccessTokenRsp(long sn, String websCode) {
        this(sn, websCode, null, new AccessToken());
    }
}
