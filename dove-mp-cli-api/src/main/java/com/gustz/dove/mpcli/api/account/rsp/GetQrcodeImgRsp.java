package com.gustz.dove.mpcli.api.account.rsp;

import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.Attachment;

/**
 * TODO: 获取二维码文件的响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
public class GetQrcodeImgRsp extends AbstBaseRsp<Attachment> {

    private static final long serialVersionUID = 1L;

    public GetQrcodeImgRsp(long sn, String websCode) {
        super(sn, websCode, null, new Attachment());
    }

}
