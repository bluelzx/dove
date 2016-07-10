package com.gustz.dove.mpcli.api.material.service;

import javax.jws.WebService;

import com.sinovatech.rd.wcsb.cli.api.material.req.GetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.req.SetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.GetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.SetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 素材服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 8, 2015 ]
 */
@WebService
public interface MaterialMpService {

    /**
     * 上传多媒体类文件
     * 
     * @param sn
     * @param cliAppCode
     * @param fileType
     * @param req
     * @return
     */
    SetMediaFileRsp uploadMediaFile(long sn, String cliAppCode, MsgTypeDict fileType, SetMediaFileReq req);

    /**
     * 获取多媒体类文件
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    GetMediaFileRsp getMediaFile(long sn, String cliAppCode, GetMediaFileReq req);

}