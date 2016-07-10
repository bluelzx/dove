package com.gustz.dove.cli.api.material.rsp;

import com.gustz.dove.cli.api.service.vo.AbstBaseRsp;
import com.gustz.dove.cli.api.service.vo.Attachment;

/**
 * TODO: 获取媒体类文件的响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
public class GetMediaFileRsp extends AbstBaseRsp<Attachment> {

    private static final long serialVersionUID = 1L;

    public GetMediaFileRsp(long sn, String websCode) {
        super(sn, websCode, null, new Attachment());
    }

}
