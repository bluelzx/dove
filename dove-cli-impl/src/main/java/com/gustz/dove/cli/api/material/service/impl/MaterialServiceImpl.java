package com.gustz.dove.cli.api.material.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.sinovatech.rd.wcsb.cli.api.material.req.GetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.req.SetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.GetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.SetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.material.service.MaterialService;
import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;
import com.gustz.dove.cli.api.service.conf.WebsUrlParam;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.gustz.dove.cli.api.service.impl.AbstBaseService;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;

/**
 * 
 * TODO: 素材服务的接口实现
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public abstract class MaterialServiceImpl extends AbstBaseService<AbstBaseReq<?>> implements MaterialService {

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
    public SetMediaFileRsp uploadMediaFile(BaseWebsUrl websUrl, long sn, String cliAppCode, MsgTypeDict fileType,
            SetMediaFileReq req) {
        //
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        Map<String, String> _map = new HashMap<String, String>();
        _map.put(WebsUrlParam.FILE_TYPE, fileType.getName()); // 文件类型
        this.setWebsUrlParamMap(_map);
        //
        return this.uploadFile(websUrl, sn, cliAppCode, req, new SetMediaFileRsp(sn, websUrl.getWebsCode()));
    }

    /**
     * 获取多媒体类文件
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    @Override
    public GetMediaFileRsp getMediaFile(BaseWebsUrl websUrl, long sn, String cliAppCode, GetMediaFileReq req) {
        //
        this.setAccessTokenX(sn, cliAppCode, req.getDevAcCode());
        Map<String, String> _map = new HashMap<String, String>();
        _map.put(WebsUrlParam.MEDIA_ID, req.getBody().getMediaId());
        this.setWebsUrlParamMap(_map);
        //
        return this.downloadFile(websUrl, sn, cliAppCode, req, new GetMediaFileRsp(sn, websUrl.getWebsCode()));
    }

}
