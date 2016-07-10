package com.gustz.dove.mpcli.api.material.service.impl;

import com.gustz.dove.mpcli.api.service.conf.MaterialWsUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.material.req.GetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.req.SetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.GetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.SetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.material.service.impl.MaterialServiceImpl;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.sinovatech.rd.wcsb.mpcli.api.material.service.MaterialMpService;
import com.sinovatech.rd.wcsb.mpcli.api.security.service.AcTokenMpService;

/**
 * 
 * TODO: 素材服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class MaterialMpServiceImpl extends MaterialServiceImpl implements MaterialMpService {

    @Autowired
    private AcTokenMpService acTokenMpService;

    /**
     * 上传多媒体类文件
     * 
     * @param sn
     * @param cliAppCode
     * @param fileType
     * @param req
     * @return
     */
    @Override
    public SetMediaFileRsp uploadMediaFile(long sn, String cliAppCode, MsgTypeDict fileType, SetMediaFileReq req) {
        //
        return this.uploadMediaFile(MaterialWsUrl.WSC05001MP, sn, cliAppCode, fileType, req);
    }

    /**
     * 获取多媒体类文件
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public GetMediaFileRsp getMediaFile(long sn, String cliAppCode, GetMediaFileReq req) {
        //
        return this.getMediaFile(MaterialWsUrl.WSC05002MP, sn, cliAppCode, req);
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenMpService.getAccessTokenMp(sn, cliAppCode, devAcCode));
    }

}
