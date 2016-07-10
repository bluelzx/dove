package com.gustz.dove.cli.api.material.service;

import com.gustz.dove.cli.api.material.req.GetMediaFileReq;
import com.gustz.dove.cli.api.material.req.SetMediaFileReq;
import com.gustz.dove.cli.api.material.rsp.GetMediaFileRsp;
import com.gustz.dove.cli.api.material.rsp.SetMediaFileRsp;
import com.gustz.dove.cli.api.service.BaseWebsUrl;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 素材服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
public interface MaterialService {

    /**
     * 上传多媒体类文件
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode
     * @param fileType
     * @param req
     * @return
     */
    SetMediaFileRsp uploadMediaFile(BaseWebsUrl websUrl, long sn, String cliAppCode, MsgTypeDict fileType, SetMediaFileReq req);

    /**
     * 获取多媒体类文件
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    GetMediaFileRsp getMediaFile(BaseWebsUrl websUrl, long sn, String cliAppCode, GetMediaFileReq req);

}