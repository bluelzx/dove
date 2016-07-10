package com.gustz.dove.cli.api.material.req;

import com.gustz.dove.cli.api.service.vo.AbstBaseReq;
import com.gustz.dove.cli.api.service.vo.UploadFileForm;

/**
 * TODO: 上传媒体类文件的请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
public class SetMediaFileReq extends AbstBaseReq<UploadFileForm[]> {

    private static final long serialVersionUID = 1L;

    public SetMediaFileReq(String devAcCode, UploadFileForm[] bodys) {
        super(devAcCode, bodys);
    }

}
