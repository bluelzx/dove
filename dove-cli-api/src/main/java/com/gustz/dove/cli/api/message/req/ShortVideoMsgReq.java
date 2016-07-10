package com.gustz.dove.cli.api.message.req;

import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 小视频请求报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class ShortVideoMsgReq extends VideoMsgReq {

    private static final long serialVersionUID = 1L;

    public ShortVideoMsgReq() {
        super.setMsgType(MsgTypeDict.SHORT_VIDEO);
    }
}
