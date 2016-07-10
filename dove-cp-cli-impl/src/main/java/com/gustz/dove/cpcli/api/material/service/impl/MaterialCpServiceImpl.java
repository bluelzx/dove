package com.gustz.dove.cpcli.api.material.service.impl;

import com.gustz.dove.cpcli.api.service.conf.MaterialWsUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.material.req.GetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.req.SetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.GetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.SetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.material.service.impl.MaterialServiceImpl;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.sinovatech.rd.wcsb.cpcli.api.material.service.MaterialCpService;
import com.sinovatech.rd.wcsb.cpcli.api.security.service.AcTokenCpService;

/**
 * 
 * TODO: 素材服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@Service
public class MaterialCpServiceImpl extends MaterialServiceImpl implements MaterialCpService {

    @Autowired
    private AcTokenCpService acTokenCpService;

    /**
     * 上传永久素材
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
        return this.uploadMediaFile(MaterialWsUrl.WSC04003CP, sn, cliAppCode, fileType, req);
    }

    /**
     * 获取永久素材
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public GetMediaFileRsp getMediaFile(long sn, String cliAppCode, GetMediaFileReq req) {
        //
        return this.getMediaFile(MaterialWsUrl.WSC04004CP, sn, cliAppCode, req);
    }

    @Override
    protected void setAccessTokenX(long sn, String cliAppCode, String devAcCode) {
        this.setAccessToken(acTokenCpService.getAccessTokenCp(sn, cliAppCode, devAcCode));
    }

}
