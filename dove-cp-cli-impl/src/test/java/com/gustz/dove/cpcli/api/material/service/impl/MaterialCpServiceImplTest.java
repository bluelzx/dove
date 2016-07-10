package com.gustz.dove.cpcli.api.material.service.impl;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.material.req.GetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.req.GetMediaFileReq.GetMediaFileBodyReq;
import com.sinovatech.rd.wcsb.cli.api.material.req.SetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.GetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.SetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.sinovatech.rd.wcsb.cli.api.service.vo.UploadFileForm;
import com.sinovatech.rd.wcsb.cpcli.api.material.service.MaterialCpService;
import com.gustz.dove.cpcli.api.service.base.CpCliTestBase;

/**
 * TODO: 素材服务的接口实现测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 13, 2015 ]
 */
public class MaterialCpServiceImplTest extends CpCliTestBase<String> {

    @Autowired
    private MaterialCpService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link MaterialCpServiceImpl#uploadMediaFile(long, java.lang.String, com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict, com.sinovatech.rd.wcsb.cpcli.api.material.req.SetMediaFileReq)}.
     */
    @Test
    public void testUploadMediaFile() {
        File file = new File("/tmp/001.jpg");
        UploadFileForm[] forms = new UploadFileForm[] { new UploadFileForm(UploadFileForm.MEDIA_KEY, file, null) };
        SetMediaFileReq req = new SetMediaFileReq(devAcCode, forms);
        //
        SetMediaFileRsp rsp = service.uploadMediaFile(sn, cliAppCode, MsgTypeDict.IMAGE, req);
        System.out.println("uploadMediaFile-rsp=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(0, rsp.getBody().getErrCode());
    }

    /**
     * Test method for {@link MaterialCpServiceImpl#getMediaFile(long, java.lang.String, com.sinovatech.rd.wcsb.cpcli.api.material.req.GetMediaFileReq)}.
     */
    @Test
    public void testGetMediaFile() {
        String mediaId = "4-seKNC5lH-kLUOK26_28Y-Ce3GQK69QMW5Zb0IgjbgsoL-SctlyILb2kfk929QV";
        GetMediaFileBodyReq body = new GetMediaFileBodyReq(mediaId);
        GetMediaFileReq req = new GetMediaFileReq(devAcCode, body);
        //
        GetMediaFileRsp rsp = service.getMediaFile(sn, cliAppCode, req);
        System.out.println("getMediaFile-rsp=:" + rsp.getBody());
        Assert.assertNotNull(rsp.getBody());
    }

}
