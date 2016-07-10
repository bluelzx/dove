package com.gustz.dove.mpcli.api.account.service;

import javax.jws.WebService;

import com.gustz.dove.mpcli.api.account.req.QrcodeImgReq;
import com.gustz.dove.mpcli.api.account.req.QrcodeReq;
import com.gustz.dove.mpcli.api.account.rsp.GetQrcodeImgRsp;
import com.gustz.dove.mpcli.api.account.rsp.QrcodeRsp;

/**
 * 
 * TODO: 二维码服务的接口
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 7, 2015 ]
 */
@WebService
public interface QrcodeMpService {

    /**
     * 生成二维码
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    QrcodeRsp createQrcode(long sn, String cliAppCode, QrcodeReq req);

    /**
     * 获取二维码图片地址
     * 
     * @param sn
     * @param cliAppCode
     * @param req
     * @return
     */
    GetQrcodeImgRsp getQrcodeImg(long sn, String cliAppCode, QrcodeImgReq req);

}