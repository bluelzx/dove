package com.gustz.dove.mpcli.api.material.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinovatech.rd.wcsb.cli.api.material.req.GetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.req.GetMediaFileReq.GetMediaFileBodyReq;
import com.sinovatech.rd.wcsb.cli.api.material.req.SetMediaFileReq;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.GetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.material.rsp.SetMediaFileRsp;
import com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.UploadFileForm;
import com.sinovatech.rd.wcsb.mpcli.api.material.service.MaterialMpService;
import com.gustz.dove.mpcli.api.service.base.MpCliTestBase;

/**
 * TODO: 素材服务的接口实现测试
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 13, 2015 ]
 */
public class MaterialMpServiceImplTest extends MpCliTestBase<String> {

    @Autowired
    private MaterialMpService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cli.api.material.service.impl.MaterialServiceImpl#uploadMediaFile(long, java.lang.String, com.sinovatech.rd.wcsb.cli.api.service.dict.MsgTypeDict, com.sinovatech.rd.wcsb.cli.api.material.req.SetMediaFileReq)}.
     */
    @Test
    public void testUploadMediaFile() {
        Map<String, String> textBody = new HashMap<String, String>();
        textBody.put("text1", "1");
        textBody.put("text2", "2");
        //
        File file = new File("/tmp/001.jpg");
        UploadFileForm[] forms = new UploadFileForm[] { new UploadFileForm(UploadFileForm.MEDIA_KEY, file, textBody) };
        SetMediaFileReq req = new SetMediaFileReq(devAcCode, forms);
        //
        SetMediaFileRsp rsp = service.uploadMediaFile(sn, cliAppCode, MsgTypeDict.IMAGE, req);
        System.out.println("uploadMediaFile-rsp=:" + rsp.getBody());
        System.out.println("uploadMediaFile-rsp2=:" + rsp.getBody().getErrMsg());
        Assert.assertEquals(ErrorBodyRsp.OK_CODE, rsp.getBody().getErrCode());
    }

    /**
     * Test method for {@link com.sinovatech.rd.wcsb.cli.api.material.service.impl.MaterialServiceImpl#getMediaFile(long, java.lang.String, com.sinovatech.rd.wcsb.cli.api.material.req.GetMediaFileReq)}.
     * @throws Exception 
     */
    @Test
    public void testGetMediaFile() throws Exception {
        String mediaId = "HdUKtZLgkGGfWd2EEgciXUpLFawDMzznviZEJda8mDbR-ydqCcZIDC4oqRxz2WmZ";
        GetMediaFileBodyReq body = new GetMediaFileBodyReq(mediaId);
        GetMediaFileReq req = new GetMediaFileReq(devAcCode, body);
        //
        GetMediaFileRsp rsp = service.getMediaFile(sn, cliAppCode, req);
        System.out.println("getMediaFile-rsp=:" + rsp.getBody());
        System.out.println("getMediaFile-rsp2=:" + rsp.getBody().getRspCode());
        Assert.assertNull(rsp.getBody().getRspCode());
    }

}
