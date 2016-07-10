package com.gustz.dove.cli.api.material.req;

import com.gustz.dove.cli.api.message.vo.Media;
import com.gustz.dove.cli.api.material.req.GetMediaFileReq.GetMediaFileBodyReq;
import com.gustz.dove.cli.api.service.vo.AbstBaseReq;

/**
 * TODO: 获取媒体类文件的请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
public class GetMediaFileReq extends AbstBaseReq<GetMediaFileBodyReq> {

    private static final long serialVersionUID = 1L;

    public GetMediaFileReq(String devAcCode, GetMediaFileBodyReq body) {
        super(devAcCode, body);
    }

    public static class GetMediaFileBodyReq extends Media {

        private static final long serialVersionUID = 1L;

        public GetMediaFileBodyReq(String mediaId) {
            super(mediaId);
        }

    }

}
